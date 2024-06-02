package by.grsu.skydiving.adapter.in.web.request;

import java.time.LocalDate;

public record UpdateJumpingRequest(
    long id,
    int accuracy,
    int memberNumber,
    LocalDate performanceDate
) {
}
