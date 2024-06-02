package by.grsu.skydiving.application.domain.model.trickRefereeing;

import lombok.Builder;

import java.util.Map;

@Builder
public record TrickAttemptsIncome(
        Long trickSerieId,
        Map<TrickType, PenaltyValues> trickAttempts
) {
}
