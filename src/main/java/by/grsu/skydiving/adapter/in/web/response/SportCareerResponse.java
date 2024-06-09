package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.skydiver.SportRank;
import by.grsu.skydiving.application.domain.model.skydiver.SportTitle;
import java.time.LocalDate;

public record SportCareerResponse(
    LocalDate beginDateOfSportCareer,
    SportTitle sportTitle,
    SportRank sportRank
) {
}
