package by.grsu.skydiving.adapter.in.web.response;

public record TrickSerieOfSkydiverResponse(
        Integer skydiverNumber,
        Integer serieNumber,
        Integer roundNumber,
        Float score,
        TrickSerieExtendedResponse trickSerieWithPenalties
) {
}
