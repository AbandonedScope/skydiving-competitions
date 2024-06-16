package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.entity.TrickAttemptEntity;
import by.grsu.skydiving.adapter.out.persistence.mapper.TrickAttemptEntityMapper;
import by.grsu.skydiving.adapter.out.persistence.repository.TrickAttemptJdbcRepository;
import by.grsu.skydiving.application.domain.model.trickRefereeing.PenaltyValues;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickAttempt;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickAttemptsIncome;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickType;
import by.grsu.skydiving.application.port.out.SaveTrickAttemptsPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class TrickAttemptPersistenceAdapter implements SaveTrickAttemptsPort {
    private final TrickAttemptJdbcRepository trickAttemptJdbcRepository;
    private final TrickAttemptEntityMapper mapper;

    public List<TrickAttempt> saveAll(TrickAttemptsIncome trickAttempts) {
        List<TrickAttemptEntity> entities = new ArrayList<>();
        trickAttempts.trickAttempts()
            .forEach((key, value) -> entities.add(mapToEntity(trickAttempts.trickSerieId(), key, value)));

        List<TrickAttemptEntity> savedEntities = trickAttemptJdbcRepository.saveAll(entities);

        return savedEntities.stream()
                .map(mapper::mapToDomain)
                .toList();
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
}
