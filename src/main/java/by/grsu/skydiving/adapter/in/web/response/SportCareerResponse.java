package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.skydiver.SportDegree;

import java.time.LocalDate;

public record SportCareerResponse(
        LocalDate beginDateOfSportCareer,
        String sportSpecialization,
        SportDegree sportDegree
) {
}
