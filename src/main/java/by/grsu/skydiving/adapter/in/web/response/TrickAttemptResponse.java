package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.trick.TrickType;
import java.util.List;

public record TrickAttemptResponse(
    Long id,
    Long trickSerieId,
    TrickType type,
    List<PenaltyMetricsResponse> penalties
) {
}
