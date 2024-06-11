package by.grsu.skydiving.adapter.out.persistence.entity.projection;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record SkydiverShortInfoProjection(
    Long id,
    String firstName,
    String secondName,
    String patronymic,
    LocalDate beginDateOfSportCareer,
    Integer sportRank,
    Integer gender,
    Boolean isInternal
) {
}
