package by.grsu.skydiving.adapter.in.web.request;

import java.time.LocalDate;

public record CreateJumpingRequest(
    int accuracy,
    int memberNumber,
    LocalDate performanceDate
) {
}
