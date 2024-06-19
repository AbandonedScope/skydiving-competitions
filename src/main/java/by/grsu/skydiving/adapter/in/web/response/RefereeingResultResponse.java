package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.trick.PenaltyReason;
import by.grsu.skydiving.application.domain.model.trick.TrickType;
import java.util.Map;

public record RefereeingResultResponse(
    Long id,
    Long refereeId,
    Long refereeNumber,
    Float timeWithoutPenalty,
    Float totalPenalty,
    Float totalTime,
    Boolean isTimeSubmitted,
    PenaltyReason penaltyReason,
    Map<TrickType, TrickAttemptRefereeingResponse> trickAttempts
) {
}
