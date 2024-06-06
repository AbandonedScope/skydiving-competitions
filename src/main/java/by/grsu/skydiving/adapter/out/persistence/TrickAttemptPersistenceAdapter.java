package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.TrickAttemptEntity;
import by.grsu.skydiving.application.domain.model.trickRefereeing.PenaltyMetrics;
import by.grsu.skydiving.application.domain.model.trickRefereeing.PenaltyType;
import by.grsu.skydiving.application.domain.model.trickRefereeing.PenaltyValues;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickAttempt;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickAttemptsIncome;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickType;
import by.grsu.skydiving.application.port.out.SaveTrickAttemptsPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class TrickAttemptPersistenceAdapter implements SaveTrickAttemptsPort {
    private final TrickAttemptJdbcRepository trickAttemptJdbcRepository;

    public List<TrickAttempt> saveAll(TrickAttemptsIncome trickAttempts) {
        List<TrickAttemptEntity> entities = new ArrayList<>();
        trickAttempts.trickAttempts()
            .forEach((key, value) -> entities.add(mapToEntity(trickAttempts.trickSerieId(), key, value)));

        List<TrickAttemptEntity> savedEntities = trickAttemptJdbcRepository.saveAll(entities);
        List<TrickAttempt> attemptsDomains = new ArrayList<>();
        savedEntities.forEach(x -> attemptsDomains.add(mapToDomain(x)));

        return attemptsDomains;
    }

    private TrickAttemptEntity mapToEntity(Long trickSerieId, TrickType trickType, PenaltyValues penalties) {
        return TrickAttemptEntity.builder()
            .trickSerieId(trickSerieId)
            .trickType(trickType.ordinal())
            .arrowPenalty(penalties.arrowPenalty())
            .dPenalty(penalties.dPenalty())
            .sPenalty(penalties.sPenalty())
            .plusMinusPenalty(penalties.plusMinusPenalty())
            .minusPenalty(penalties.minusPenalty())
            .build();
    }

    private TrickAttempt mapToDomain(TrickAttemptEntity entity) {
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

    private PenaltyMetrics getPenaltyMetrics(PenaltyType type, Integer value) {
        PenaltyMetrics metrics = PenaltyMetrics.builder()
            .penaltyValue(value)
            .penaltyType(type)
            .build();
        metrics = metrics.withPenaltyValueTime(metrics.getPenaltyTimeFromPenalty());

        return metrics;
    }
}
