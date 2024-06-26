package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.skydiver.Gender;
import by.grsu.skydiving.application.domain.model.skydiver.SportRank;

public record ExternalSkydiverResponse(
    Long id,
    FullNameResponse name,
    Gender gender,
    SportRank sportRank
) {
}
