package by.grsu.skydiving.application.domain.model.trickRefereeing;

import lombok.Builder;

@Builder
public record TrickSerieTime(
        Long trickSerieId,
        Float timeWithoutPenalty
) {
}
