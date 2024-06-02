package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickType;

import java.util.Map;

public record TrickAttemptsFullInfoResponse(
        Float totalScore,
        Map<TrickType, TrickAttemptResponse> trickAttempts
) {
}
