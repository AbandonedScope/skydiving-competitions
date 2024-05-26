package by.grsu.skydiving.adapter.in.web.request;

public record AddTrickRefereeingRequest(
        Long competitionId,
        Long skydiverId,
        Integer serieNumber,
        Integer roundNumber
) {
}
