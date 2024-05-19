package by.grsu.skydiving.application.domain.model.common;

import by.grsu.skydiving.application.domain.model.skydiver.Gender;
import by.grsu.skydiving.application.domain.model.skydiver.SportDegree;
import lombok.Builder;

@Builder
public record SkydiverFilterQuery(
        SportDegree sportDegree,
        Gender gender,
        String name,
        Boolean isInternal
) {
}
