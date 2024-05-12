package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.skydiver.Gender;

public record SkydiverShortInfoResponse(
        Long id,
        FullNameResponse name,
        SportCareerResponse career,
        Gender gender,
        boolean isInternal
) {
}
