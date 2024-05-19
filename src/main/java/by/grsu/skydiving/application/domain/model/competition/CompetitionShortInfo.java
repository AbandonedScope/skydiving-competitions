package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.model.skydiver.Address;
import java.time.LocalDate;

public record CompetitionShortInfo(
    Long id,
    String name,
    LocalDate beginDate,
    LocalDate endDate,
    Address place,
    CompetitionStatus status
) {
}
