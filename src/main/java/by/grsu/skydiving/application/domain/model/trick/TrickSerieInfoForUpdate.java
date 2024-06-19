package by.grsu.skydiving.application.domain.model.trick;

public record TrickSerieInfoForUpdate(
        Long trickSerieId,
        Boolean isTimeSubmitted,
        Float timeWithoutPenalty
) {
}
