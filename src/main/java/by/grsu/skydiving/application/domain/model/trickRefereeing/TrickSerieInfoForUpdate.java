package by.grsu.skydiving.application.domain.model.trickRefereeing;

public record TrickSerieInfoForUpdate(
        Long trickSerieId,
        Boolean isTimeSubmitted,
        Float timeWithoutPenalty
) {
}
