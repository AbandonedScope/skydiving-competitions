package by.grsu.skydiving.adapter.in.web.response;

public record TrickSerieShortInfoResponse(
        Long id,
        Long refereeId,
        Long refereeNumber,
        Integer serieNumber,
        Integer roundNumber,
        Integer score,
        Float timeWithoutPenalty,
        Float totalPenalty,
        Boolean isTimeSubmitted
) {
}
