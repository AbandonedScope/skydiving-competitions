package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.adapter.out.persistence.TrickAttemptPersistenceAdapter;
import by.grsu.skydiving.application.domain.model.trickRefereeing.*;
import by.grsu.skydiving.application.port.in.AddTrickAttemptsUseCase;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UseCase
@RequiredArgsConstructor
public class AddTrickAttemptService implements AddTrickAttemptsUseCase {
    private final TrickAttemptPersistenceAdapter trickAttemptPersistenceAdapter;

    @Override
    public TrickAttemptsWithScore addTrickAttempts(AddTrickAttemptToTrickSerieCommand command) {
        TrickAttemptsIncome attempts = TrickAttemptsIncome.builder()
            .trickSerieId(command.trickSerieId())
            .penaltyReason(command.penaltyReason())
            .trickAttempts(command.trickAttempts())
            .build();

        List<TrickAttempt> savedAttempts = trickAttemptPersistenceAdapter.saveAll(attempts);

        return mapToFullDomainModel(savedAttempts, command.penaltyReason());
    }

    private TrickAttemptsWithScore mapToFullDomainModel(List<TrickAttempt> attempts, PenaltyReason penaltyReason) {
        Map<TrickType, TrickAttempt> tricksMap = HashMap.newHashMap(6);
        attempts.forEach(x -> tricksMap.put(x.trickType(), x));
        float totalScore = Math.round(TrickAttemptsWithScore.calculateTotalPenalty(penaltyReason,attempts)* 10f)  /10f;

        return TrickAttemptsWithScore.builder()
            .trickAttempts(tricksMap)
            .penaltyReason(penaltyReason)
            .totalScore(totalScore)
            .build();
    }
}
