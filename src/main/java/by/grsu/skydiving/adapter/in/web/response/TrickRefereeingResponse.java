package by.grsu.skydiving.adapter.in.web.response;

import java.util.List;

public record TrickRefereeingResponse(
    Integer skydiverNumber,
    Long skydiverId,
    Long competitionId,
    Integer serieNumber,
    Integer roundNumber,
    List<TrickSerieShortInfoResponse> trickSeries
) {
}
