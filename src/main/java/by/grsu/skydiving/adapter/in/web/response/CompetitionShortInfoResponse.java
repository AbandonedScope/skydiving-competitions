package by.grsu.skydiving.adapter.in.web.response;

import java.time.LocalDate;

public record CompetitionShortInfoResponse(
    long id,
    String name,
    LocalDate beginDate,
    LocalDate endDate,
    String place
) {
}
