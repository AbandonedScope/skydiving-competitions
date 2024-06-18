package by.grsu.skydiving.adapter.in.web.response;

public record UpdatedTrickSerieResponse(
        Long trickSerieId,
        Boolean isTimeSubmitted,
        Float timeWithoutPenalty
) {
}
