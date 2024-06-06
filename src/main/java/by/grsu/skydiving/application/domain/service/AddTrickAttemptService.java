package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.adapter.out.persistence.repository.TrickAttemptPersistenceAdapter;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickAttempt;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickAttemptsIncome;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickAttemptsWithScore;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickType;
import by.grsu.skydiving.application.port.in.AddTrickAttemptsUseCase;
import by.grsu.skydiving.common.UseCase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddTrickAttemptService implements AddTrickAttemptsUseCase {
    private final TrickAttemptPersistenceAdapter trickAttemptPersistenceAdapter;

    @Override
    public TrickAttemptsWithScore addTrickAttempts(AddTrickAttemptToTrickSerieCommand command) {
        TrickAttemptsIncome attempts = TrickAttemptsIncome.builder()
            .trickSerieId(command.trickSerieId())
            .trickAttempts(command.trickAttempts())
            .build();

        List<TrickAttempt> savedAttempts = trickAttemptPersistenceAdapter.saveAll(attempts);

        return mapToFullDomainModel(savedAttempts);
    }

    private TrickAttemptsWithScore mapToFullDomainModel(List<TrickAttempt> attempts) {
        Map<TrickType, TrickAttempt> tricksMap = HashMap.newHashMap(6);
        attempts.forEach(x -> tricksMap.put(x.trickType(), x));

        return TrickAttemptsWithScore.builder()
            .trickAttempts(tricksMap)
            .totalScore(TrickAttemptsWithScore.calculateTotalPenalty(attempts))
            .build();
    }
}
