package by.grsu.skydiving.adapter.in.web.request;

import java.time.LocalDate;

public record InitiateCompetitionRequest(
        String name,
        LocalDate beginDate,
        LocalDate endDate,
        String place,
        Integer numberOfStages
) {
}
