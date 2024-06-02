package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.trickRefereeing.PenaltyType;

public record PenaltyMetricsResponse(
        PenaltyType penaltyType,
        Integer penaltyValue,
        Float penaltyValueTime
) {
}
