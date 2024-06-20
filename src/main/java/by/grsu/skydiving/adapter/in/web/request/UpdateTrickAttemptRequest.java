package by.grsu.skydiving.adapter.in.web.request;

public record UpdateTrickAttemptRequest(
        Long id,
        Integer arrowPenalty,
        Integer dPenalty,
        Integer sPenalty,
        Integer minusPenalty,
        Integer plusMinusPenalty
) {
}
