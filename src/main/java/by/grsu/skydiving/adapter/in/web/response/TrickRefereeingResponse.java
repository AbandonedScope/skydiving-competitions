package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickSerie;

import java.util.List;

public record TrickRefereeingResponse(
        Integer skydiverNumber,
        Integer serieNumber,
        Integer roundNumber,
        List<TrickSerieShortInfoResponse> trickSeries
) {
}
