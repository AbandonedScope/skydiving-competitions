package by.grsu.skydiving.application.domain.model.trickRefereeing;

import java.time.LocalDate;

public record CompetitionForRefereeing(
    Long competitionId,
    String name,
    LocalDate beginDate,
    LocalDate endDate,
    String address,
    Integer numberOfStages
) {
}
