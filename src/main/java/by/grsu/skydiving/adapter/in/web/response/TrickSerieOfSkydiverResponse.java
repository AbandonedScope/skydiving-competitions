package by.grsu.skydiving.adapter.in.web.response;

import java.util.List;

public record TrickSerieOfSkydiverResponse(
    Integer memberNumber,
    Integer serieNumber,
    Integer roundNumber,
    Float totalScore,
    List<RefereeingResultResponse> refereeingResults
) {
}
