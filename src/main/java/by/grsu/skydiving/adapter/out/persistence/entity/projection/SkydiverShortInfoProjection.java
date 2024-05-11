package by.grsu.skydiving.adapter.out.persistence.entity.projection;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SkydiverShortInfoProjection(
        Long id,
        String firstName,
        String secondName,
        String patronymic,
        LocalDate beginDateOfSportCareer,
        String sportSpecialization,
        Integer sportDegree,
        Integer gender,
        Boolean isInternal
) {
}
