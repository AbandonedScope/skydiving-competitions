package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.trick.PenaltyReason;
import by.grsu.skydiving.application.domain.model.trick.TrickType;

import java.util.Map;

public record UpdateTrickAttemptsResponse(
        Long trickSerieId,
        PenaltyReason penaltyReason,
        Float totalScore,
        Map<TrickType, UpdateTrickAttemptResponse> attempts
) {
}
