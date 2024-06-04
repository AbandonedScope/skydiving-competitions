package by.grsu.skydiving.adapter.in.web.response;

public record NextJumpingNumberResponse(
    Integer nextJumpingNumber,
    boolean isLimitReached
) {
}
