package by.grsu.skydiving.adapter.in.web.response;

import java.time.LocalDate;

public record JumpingInfoResponse(
    long id,
    int number,
    int accuracy,
    LocalDate performanceDate
) {
}
