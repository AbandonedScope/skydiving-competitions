package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.TrickAttemptEntity;
import by.grsu.skydiving.application.domain.model.trick.*;

import java.util.*;

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

    default TrickAttemptRefereeing mapToDomainTrickAttemptRefereeing(TrickAttemptEntity entity) {
        Map<PenaltyType, PenaltyMetrics> metricsMap = new EnumMap<>(PenaltyType.class);

        metricsMap.put(PenaltyType.ARROW_PENALTY,
            getPenaltyMetrics(PenaltyType.ARROW_PENALTY, entity.getArrowPenalty()));
        metricsMap.put(PenaltyType.S_PENALTY, getPenaltyMetrics(PenaltyType.S_PENALTY, entity.getSPenalty()));
        metricsMap.put(PenaltyType.D_PENALTY, getPenaltyMetrics(PenaltyType.D_PENALTY, entity.getDPenalty()));
        metricsMap.put(PenaltyType.PLUS_MINUS_PENALTY,
            getPenaltyMetrics(PenaltyType.PLUS_MINUS_PENALTY, entity.getPlusMinusPenalty()));
        metricsMap.put(PenaltyType.MINUS_PENALTY,
            getPenaltyMetrics(PenaltyType.MINUS_PENALTY, entity.getMinusPenalty()));

        return TrickAttemptRefereeing.builder()
            .id(entity.getId())
            .trickType(TrickType.of(entity.getTrickType()))
            .trickSerieId(entity.getTrickSerieId())
            .penalties(metricsMap)
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

    default List<TrickAttemptEntity> mapToEntities(TrickAttemptsUpdate modelForUpdate) {
        List<TrickAttemptEntity> entities = new ArrayList<>();
        modelForUpdate.attempts().forEach((x, y) -> entities.add(
                TrickAttemptEntity.builder()
                .id(y.id())
                .trickSerieId(modelForUpdate.trickSerieId())
                .trickType(x.ordinal())
                .arrowPenalty(y.arrowPenalty())
                .dPenalty(y.dPenalty())
                .sPenalty(y.sPenalty())
                .plusMinusPenalty(y.plusMinusPenalty())
                .minusPenalty(y.minusPenalty())
                .build()));

        return entities;
    }

    default TrickAttemptsUpdate mapToDomain(List<TrickAttemptEntity> entities, PenaltyReason reason, Long trickSerieId){
        Map<TrickType, TrickAttemptForUpdate> domainAttempts = new HashMap<>();
        entities.forEach(x -> domainAttempts.put(TrickType.of(x.getTrickType()),
                TrickAttemptForUpdate.builder()
                        .id(x.getId())
                        .dPenalty(x.getDPenalty())
                        .arrowPenalty(x.getArrowPenalty())
                        .plusMinusPenalty(x.getPlusMinusPenalty())
                        .sPenalty(x.getSPenalty())
                        .minusPenalty(x.getMinusPenalty())
                        .build()));

        return TrickAttemptsUpdate.builder()
                .penaltyReason(reason)
                .attempts(domainAttempts)
                .trickSerieId(trickSerieId)
                .build();
    }
}
