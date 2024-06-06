package by.grsu.skydiving.adapter.in.web.request;

public record PenaltyMetricsRequest(
    int arrowPenalty,
    int dPenalty,
    int sPenalty,
    int minusPenalty,
    int plusMinusPenalty
) {
}
