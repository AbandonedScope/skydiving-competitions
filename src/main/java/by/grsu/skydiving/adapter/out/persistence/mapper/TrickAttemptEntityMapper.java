package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.TrickAttemptEntity;
import by.grsu.skydiving.application.domain.model.trick.PenaltyMetrics;
import by.grsu.skydiving.application.domain.model.trick.PenaltyType;
import by.grsu.skydiving.application.domain.model.trick.TrickAttempt;
import by.grsu.skydiving.application.domain.model.trick.TrickType;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface TrickAttemptEntityMapper {
    TrickAttemptEntity toEntity(List<PenaltyMetrics> metrics, Long trickId);

    default TrickAttempt mapToDomain(TrickAttemptEntity entity) {
        List<PenaltyMetrics> metrics = new ArrayList<>();
        metrics.add(getPenaltyMetrics(PenaltyType.ARROW_PENALTY, entity.getArrowPenalty()));
        metrics.add(getPenaltyMetrics(PenaltyType.S_PENALTY, entity.getSPenalty()));
        metrics.add(getPenaltyMetrics(PenaltyType.D_PENALTY, entity.getDPenalty()));
        metrics.add(getPenaltyMetrics(PenaltyType.PLUS_MINUS_PENALTY, entity.getPlusMinusPenalty()));
        metrics.add(getPenaltyMetrics(PenaltyType.MINUS_PENALTY, entity.getMinusPenalty()));

        return TrickAttempt.builder()
                .id(entity.getId())
                .trickType(TrickType.of(entity.getTrickType()))
                .trickSerieId(entity.getTrickSerieId())
                .penalties(metrics)
                .build();
    }

    default PenaltyMetrics getPenaltyMetrics(PenaltyType type, Integer value) {
        PenaltyMetrics metrics = PenaltyMetrics.builder()
                .penaltyValue(value)
                .penaltyType(type)
                .build();
        metrics = metrics.withPenaltyValueTime(metrics.getPenaltyTimeFromPenalty());

        return metrics;
    }

    default Integer mapTrickType(TrickType trickType) {
        return trickType == null
            ? null
            : trickType.ordinal();
    }

    default TrickType mapSportDegree(Integer ordinal) {
        return ordinal == null
            ? null
            : TrickType.of(ordinal);
    }

    default Integer mapPenaltyType(PenaltyType penaltyType) {
        return penaltyType == null
            ? null
            : penaltyType.ordinal();
    }

    default PenaltyType mapPenaltyType(Integer ordinal) {
        return ordinal == null
            ? null
            : PenaltyType.of(ordinal);
    }
}
