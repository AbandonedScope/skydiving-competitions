package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.trick.PenaltyType;

public record PenaltyMetricsResponse(
    PenaltyType penaltyType,
    Integer penaltyValue,
    Float penaltyValueTime
) {
}
