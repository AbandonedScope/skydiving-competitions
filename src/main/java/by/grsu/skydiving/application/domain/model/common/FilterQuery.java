package by.grsu.skydiving.application.domain.model.common;

import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import by.grsu.skydiving.application.domain.model.skydiver.Gender;

public record FilterQuery(
        String sportDegree,
        Gender gender,
        FullName name,
        Boolean isInternal
) {
}
