package by.grsu.skydiving.application.domain.model.trick;

import lombok.Builder;

@Builder
public record TrickSerieTime(
        Long trickSerieId,
        Float timeWithoutPenalty
) {
}
