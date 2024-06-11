package by.grsu.skydiving.application.domain.model.pivot;

import lombok.Builder;

@Builder
public record JumpingShortInfo(
    Long jumpingId,
    Integer accuracy,
    Integer number
) {
}
