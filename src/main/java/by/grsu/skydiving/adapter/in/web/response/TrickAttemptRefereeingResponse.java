package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.trick.PenaltyType;
import by.grsu.skydiving.application.domain.model.trick.TrickType;
import java.util.Map;

public record TrickAttemptRefereeingResponse(
    Long id,
    Long trickSerieId,
    TrickType type,
    Map<PenaltyType, PenaltyMetricsResponse> penalties
) {
}