package by.grsu.skydiving.application.domain.model.trick;

public record Refereeing(
    Long trickSerieId,
    Integer roundNumber,
    Integer serieNumber,
    CompetitionForRefereeing competition
) {
}
