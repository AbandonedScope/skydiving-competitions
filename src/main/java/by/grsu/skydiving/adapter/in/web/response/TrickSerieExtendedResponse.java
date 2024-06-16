package by.grsu.skydiving.adapter.in.web.response;

public record TrickSerieExtendedResponse(
        Long id,
        Long refereeId,
        Long refereeNumber,
        Float timeWithoutPenalty,
        Float totalPenalty,
        Boolean isTimeSubmitted,
        TrickAttemptsFullInfoResponse trickAttemptsWithScore
) {
}
