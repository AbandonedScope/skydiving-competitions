package by.grsu.skydiving.adapter.in.web.response;

public record UpdateTrickAttemptResponse(
        Long id,
        Integer arrowPenalty,
        Integer dPenalty,
        Integer sPenalty,
        Integer minusPenalty,
        Integer plusMinusPenalty
) {
}
