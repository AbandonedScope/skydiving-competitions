package by.grsu.skydiving.application.domain.model.trick;

public record TrickSerieShortInfo(
        Long trickSerieId,
        Integer roundNumber,
        Integer serieNumber,
        String competitionName
) {
}
