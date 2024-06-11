package by.grsu.skydiving.application.domain.model.skydiver;

import java.time.LocalDate;

public record SportCareer(
    LocalDate beginDateOfSportCareer,
    SportRank sportRank
) {
}
