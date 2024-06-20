package by.grsu.skydiving.adapter.in.web.response;

import java.util.List;

public record TrickSerieOfSkydiverResponse(
    Integer memberNumber,
    Integer serieNumber,
    Integer roundNumber,
    Float totalScore,
    boolean isCompleted,
    List<RefereeingResultResponse> refereeingResults
) {
}
