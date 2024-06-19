package by.grsu.skydiving.application.domain.model.trick;

import lombok.Builder;

@Builder(toBuilder = true)
public record TrickSerieInfoForUpdate(
        Long trickSerieId,
        Boolean isTimeSubmitted,
        Float timeWithoutPenalty
) {
}
