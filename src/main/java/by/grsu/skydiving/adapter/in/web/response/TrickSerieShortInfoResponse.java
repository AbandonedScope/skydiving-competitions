package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.trick.PenaltyReason;

public record TrickSerieShortInfoResponse(
    Long id,
    Long refereeId,
    Long refereeNumber,
    Long skydiverId,
    Long competitionId,
    Integer serieNumber,
    Integer roundNumber,
    Integer score,
    Float timeWithoutPenalty,
    Float totalPenalty,
    PenaltyReason penaltyReason,
    Boolean isTimeSubmitted
) {
}
