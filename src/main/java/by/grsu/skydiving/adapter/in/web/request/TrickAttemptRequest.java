package by.grsu.skydiving.adapter.in.web.request;

import by.grsu.skydiving.application.domain.model.trick.PenaltyReason;
import by.grsu.skydiving.application.domain.model.trick.TrickType;
import java.util.Map;

public record TrickAttemptRequest(
    Long trickSerieId,
    PenaltyReason penaltyReason,
    Map<TrickType, PenaltyMetricsRequest> trickAttempts
) {
}
