package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.trickRefereeing.PenaltyReason;

public record TrickSerieExtendedResponse(
        Long id,
        Long refereeId,
        Long refereeNumber,
        Float timeWithoutPenalty,
        Float totalPenalty,
        Boolean isTimeSubmitted,
        PenaltyReason penaltyReason,
        TrickAttemptsFullInfoResponse trickAttemptsWithScore
) {
}
