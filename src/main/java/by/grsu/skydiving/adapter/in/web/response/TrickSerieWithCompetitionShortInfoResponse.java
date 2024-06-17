package by.grsu.skydiving.adapter.in.web.response;

public record TrickSerieWithCompetitionShortInfoResponse(
        Long trickSerieId,
        Integer roundNumber,
        Integer serieNumber,
        String competitionName
) {
}
