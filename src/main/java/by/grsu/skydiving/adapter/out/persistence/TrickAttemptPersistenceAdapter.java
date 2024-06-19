package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.entity.TrickAttemptEntity;
import by.grsu.skydiving.adapter.out.persistence.mapper.TrickAttemptEntityMapper;
import by.grsu.skydiving.adapter.out.persistence.repository.TrickAttemptJdbcRepository;
import by.grsu.skydiving.adapter.out.persistence.repository.TrickSerieJdbcRepository;
import by.grsu.skydiving.application.domain.model.trick.PenaltyValues;
import by.grsu.skydiving.application.domain.model.trick.TrickAttempt;
import by.grsu.skydiving.application.domain.model.trick.TrickAttemptsIncome;
import by.grsu.skydiving.application.domain.model.trick.TrickType;
import by.grsu.skydiving.application.port.out.SaveTrickAttemptsPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class TrickAttemptPersistenceAdapter implements SaveTrickAttemptsPort {
    private final TrickAttemptJdbcRepository trickAttemptJdbcRepository;
    private final TrickSerieJdbcRepository trickSerieJdbcRepository;
    private final TrickAttemptEntityMapper mapper;

    public List<TrickAttempt> saveAll(TrickAttemptsIncome trickAttempts) {
        List<TrickAttemptEntity> entities = new ArrayList<>();
        trickAttempts.trickAttempts()
            .forEach((key, value) -> entities.add(mapToEntity(trickAttempts.trickSerieId(), key, value)));

        List<TrickAttemptEntity> savedEntities = trickAttemptJdbcRepository.saveAll(entities);
        trickSerieJdbcRepository.setPenaltyReason(trickAttempts.trickSerieId(), trickAttempts.penaltyReason().ordinal());

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
