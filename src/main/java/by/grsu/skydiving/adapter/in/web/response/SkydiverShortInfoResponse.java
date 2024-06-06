package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.skydiver.Gender;
import by.grsu.skydiving.application.domain.model.skydiver.SportDegree;

public record SkydiverShortInfoResponse(
    Long id,
    FullNameResponse name,
    SportDegree sportCareer,
    Gender gender,
    boolean isInternal
) {
}
