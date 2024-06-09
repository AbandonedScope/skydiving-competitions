package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.skydiver.Gender;
import by.grsu.skydiving.application.domain.model.skydiver.SportRank;
import by.grsu.skydiving.application.domain.model.skydiver.SportTitle;

public record SkydiverShortInfoResponse(
    Long id,
    FullNameResponse name,
    SportTitle sportTitle,
    SportRank sportRank,
    Gender gender,
    boolean isInternal
) {
}
