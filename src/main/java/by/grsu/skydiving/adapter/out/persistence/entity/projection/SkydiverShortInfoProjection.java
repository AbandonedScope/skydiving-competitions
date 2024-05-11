package by.grsu.skydiving.adapter.out.persistence.entity.projection;

import java.time.LocalDate;

public record SkydiverShortInfoProjection(
        Long id,
        String firstName,
        String secondName,
        String patronymic,
        LocalDate beginDateOfSportCareer,
        String sportSpecialization,
        Integer sportDegree
) {
}
