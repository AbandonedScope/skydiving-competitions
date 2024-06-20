package by.grsu.skydiving.adapter.in.web.request;

public record UpdateTrickAttemptRequest(
        Long id,
        int arrowPenalty,
        int dPenalty,
        int sPenalty,
        int minusPenalty,
        int plusMinusPenalty
) {
}
