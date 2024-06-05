package by.grsu.skydiving.application.domain.model.trickRefereeing;

public record Refereeing(
        Long trickSerieId,
        Integer roundNumber,
        Integer serieNumber,
        CompetitionForRefereeing competition
) {
}
