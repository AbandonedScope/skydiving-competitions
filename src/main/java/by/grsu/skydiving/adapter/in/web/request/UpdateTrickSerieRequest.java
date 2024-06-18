package by.grsu.skydiving.adapter.in.web.request;

public record UpdateTrickSerieRequest(
        Long trickSerieId,
        Boolean isTimeSubmitted,
        Float timeWithoutPenalty
) {
}
