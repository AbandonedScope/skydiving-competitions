package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.entity.TrickAttemptEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.TrickSerieProjection;
import by.grsu.skydiving.adapter.out.persistence.mapper.TrickAttemptEntityMapper;
import by.grsu.skydiving.adapter.out.persistence.repository.TrickAttemptJdbcRepository;
import by.grsu.skydiving.adapter.out.persistence.repository.TrickSerieJdbcRepository;
import by.grsu.skydiving.application.domain.model.trick.PenaltyReason;
import by.grsu.skydiving.application.domain.model.trick.RefereeingResult;
import by.grsu.skydiving.application.domain.model.trick.TrickAttemptRefereeing;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieOfSkydiver;
import by.grsu.skydiving.application.domain.model.trick.TrickType;
import by.grsu.skydiving.application.port.out.GetTrickSeriesByCompetitionIdPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class SkydiverTrickSeriePersistenceAdapter implements GetTrickSeriesByCompetitionIdPort {
    private final TrickSerieJdbcRepository trickSerieJdbcRepository;
    private final TrickAttemptJdbcRepository trickAttemptJdbcRepository;
    private final TrickAttemptEntityMapper mapper;

    @Override
    public List<TrickSerieOfSkydiver> getByCompetitionId(Long competitionId) {
        List<TrickSerieProjection> trickSeries = trickSerieJdbcRepository.getTrickSeriesByCompetitionId(competitionId);
        List<TrickAttemptEntity> trickAttempts = trickAttemptJdbcRepository.getTrickAttemptByCompetitionId(competitionId);

        Map<TrickSeriesEmbeddedId, List<TrickSerieProjection>> m = trickSeries.stream()
            .collect(Collectors.groupingBy(
                trickSerieProjection -> new TrickSeriesEmbeddedId(
                    trickSerieProjection.getSkydiverNumber(),
                    trickSerieProjection.getSerieNumber(),
                    trickSerieProjection.getRoundNumber()
                )
            ));


        return m.entrySet().stream()
            .map(entry -> mapTrickSeries(entry.getKey(), entry.getValue(), trickAttempts))
            .toList();
    }

    private TrickSerieOfSkydiver mapTrickSeries(TrickSeriesEmbeddedId trickSeriesEmbeddedId,
                                                List<TrickSerieProjection> trickSeriesProjections,
                                                List<TrickAttemptEntity> trickAttempts) {

        var refereeingResults = trickSeriesProjections.stream()
            .map(trickSerieProjection -> {
                List<TrickAttemptEntity> attempts = filterByTrickSeriesId(trickSerieProjection.getId(), trickAttempts);
                return mapToRefereeingResult(trickSerieProjection, attempts);
            })
            .toList();

        Float score = TrickSerieOfSkydiver.calculateTotalScore(refereeingResults);

        return TrickSerieOfSkydiver.builder()
            .memberNumber(trickSeriesEmbeddedId.memberNumber())
            .serieNumber(trickSeriesEmbeddedId.serieNumber())
            .roundNumber(trickSeriesEmbeddedId.roundNumber())
            .totalScore(score)
            .refereeingResults(refereeingResults)
            .build();
    }


    private RefereeingResult mapToRefereeingResult(TrickSerieProjection trickSeries,
                                                   List<TrickAttemptEntity> trickAttemptEntities) {
        var trickAttemptsList = trickAttemptEntities.stream()
            .map(mapper::mapToDomainTrickAttemptRefereeing)
            .toList();

        Float totalPenalties = RefereeingResult.calculateTotalPenalty(
            PenaltyReason.of(trickSeries.getPenaltyReason()),
            trickAttemptsList
        );

        Float totalTime;
        if (trickSeries.getTimeWithoutPenalty() == null) {
            totalTime = null;
        } else {
            totalTime = trickSeries.getTimeWithoutPenalty() + totalPenalties;
            totalTime = totalTime > 16 ? 16 : totalTime;
        }

        Map<TrickType, TrickAttemptRefereeing> trickAttempts = trickAttemptsList.stream()
            .collect(Collectors.toMap(
                TrickAttemptRefereeing::trickType,
                Function.identity()
            ));

        return RefereeingResult.builder()
            .id(trickSeries.getId())
            .refereeId(trickSeries.getRefereeId())
            .refereeNumber(trickSeries.getRefereeNumber())
            .timeWithoutPenalty(trickSeries.getTimeWithoutPenalty())
            .isTimeSubmitted(trickSeries.getIsTimeSubmitted())
            .penaltyReason(PenaltyReason.of(trickSeries.getPenaltyReason()))
            .totalPenalty(totalPenalties)
            .totalTime(totalTime)
            .trickAttempts(trickAttempts)
            .build();

    }

    private List<TrickAttemptEntity> filterByTrickSeriesId(Long trickSerieId, List<TrickAttemptEntity> attempts) {
        return attempts.stream()
                .filter(x -> x.getTrickSerieId().equals(trickSerieId))
            .toList();
    }

    private record TrickSeriesEmbeddedId(
        Integer memberNumber,
        Integer serieNumber,
        Integer roundNumber
    ) {
    }
}
