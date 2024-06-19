package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.trick.PenaltyReason;
import by.grsu.skydiving.application.domain.model.trick.TrickType;
import java.util.Map;

public record TrickAttemptsFullInfoResponse(
    Float totalScore,
    PenaltyReason penaltyReason,
    Map<TrickType, TrickAttemptResponse> trickAttempts
) {
}
