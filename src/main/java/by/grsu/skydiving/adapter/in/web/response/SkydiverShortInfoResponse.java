package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.skydiver.Gender;
import by.grsu.skydiving.application.domain.model.skydiver.SportRank;

public record SkydiverShortInfoResponse(
    Long id,
    FullNameResponse name,
    SportRank sportRank,
    Gender gender,
    boolean isInternal
) {
}
