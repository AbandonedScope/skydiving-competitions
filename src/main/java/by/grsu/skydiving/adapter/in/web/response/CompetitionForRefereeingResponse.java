package by.grsu.skydiving.adapter.in.web.response;

import java.time.LocalDate;

public record CompetitionForRefereeingResponse(
        Long competitionId,
        String name,
        LocalDate beginDate,
        LocalDate endDate,
        String address,
        Integer numberOfStages
) {
}
