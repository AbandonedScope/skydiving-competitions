package by.grsu.skydiving.adapter.out.persistence.mapper;

import static generated.Tables.TRICK_ATTEMPT;
import static generated.Tables.TRICK_SERIE;

import by.grsu.skydiving.application.domain.model.pivot.AcrobaticsShortInfo;
import by.grsu.skydiving.application.domain.model.trick.PenaltyMetrics;
import by.grsu.skydiving.application.domain.model.trick.PenaltyType;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;
import org.jooq.Record;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Result;
import org.springframework.stereotype.Component;

@Component
public class AcrobaticsShortInfoRecordMapperImpl implements AcrobaticsShortInfoRecordMapper {
    @Override
    public Stream<AcrobaticsShortInfo> toDomain(
        Record2<Long, Result<Record3<Integer, BigDecimal, Result<Record>>>> jooqRecord) {
        Long competitionMemberDetailsId = jooqRecord.get(TRICK_SERIE.COMPETITION_MEMBER_DETAIL_ID);
        return jooqRecord.component2().stream()
            .map(round -> mapAcrobaticsShortInfo(competitionMemberDetailsId, round))
            .filter(acrobaticsShortInfo -> Objects.nonNull(acrobaticsShortInfo.time()));
    }

    private AcrobaticsShortInfo mapAcrobaticsShortInfo(Long competitionMemberDetailsId,
                                                       Record3<Integer, BigDecimal, Result<Record>> round) {
        int roundNumber = round.get(TRICK_SERIE.ROUND_NUMBER);
        var time = round.component2();
        var attempts = round.component3();
        var optAverage = attempts.stream()
            .mapToDouble(this::mapPenalties)
            .average();

        Float avgTime = null;
        if (time != null) {
            avgTime = (float) Math.min(16.0f,
                time.doubleValue() + optAverage.orElse(0.0)
            );
        }

        return AcrobaticsShortInfo.builder()
            .competitionMemberDetailsId(competitionMemberDetailsId)
            .number(roundNumber)
            .time(avgTime)
            .build();
    }

    private Double mapPenalties(Record attempt) {
        Integer arrowPenalty = attempt.get(TRICK_ATTEMPT.ARROW_PENALTY);
        Integer dPenalty = attempt.get(TRICK_ATTEMPT.D_PENALTY);
        Integer sPenalty = attempt.get(TRICK_ATTEMPT.S_PENALTY);
        Integer minusPenalty = attempt.get(TRICK_ATTEMPT.MINUS_PENALTY);
        Integer plusMinusPenalty = attempt.get(TRICK_ATTEMPT.PLUS_MINUS_PENALTY);

        PenaltyMetrics arrowMetrics = PenaltyMetrics.builder()
            .penaltyType(PenaltyType.ARROW_PENALTY)
            .penaltyValue(arrowPenalty)
            .build();

        PenaltyMetrics dMetrics = PenaltyMetrics.builder()
            .penaltyType(PenaltyType.D_PENALTY)
            .penaltyValue(dPenalty)
            .build();

        PenaltyMetrics sMetrics = PenaltyMetrics.builder()
            .penaltyType(PenaltyType.S_PENALTY)
            .penaltyValue(sPenalty)
            .build();

        PenaltyMetrics minusMetrics = PenaltyMetrics.builder()
            .penaltyType(PenaltyType.MINUS_PENALTY)
            .penaltyValue(minusPenalty)
            .build();

        PenaltyMetrics plusMinusMetrics = PenaltyMetrics.builder()
            .penaltyType(PenaltyType.PLUS_MINUS_PENALTY)
            .penaltyValue(plusMinusPenalty)
            .build();
        return Math.min(16.0,
            arrowMetrics.getPenaltyTimeFromPenalty()
            + dMetrics.getPenaltyTimeFromPenalty()
            + sMetrics.getPenaltyTimeFromPenalty()
            + minusMetrics.getPenaltyTimeFromPenalty()
            + plusMinusMetrics.getPenaltyTimeFromPenalty()
        );
    }
}
