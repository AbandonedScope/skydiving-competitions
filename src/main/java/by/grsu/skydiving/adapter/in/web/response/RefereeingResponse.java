package by.grsu.skydiving.adapter.in.web.response;

public record RefereeingResponse(
        Long trickSerieId,
        Integer roundNumber,
        Integer serieNumber,
        CompetitionForRefereeingResponse competition
) {
}
