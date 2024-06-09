package by.grsu.skydiving.adapter.in.web.request;

import by.grsu.skydiving.application.domain.model.skydiver.Gender;
import by.grsu.skydiving.application.domain.model.skydiver.SportRank;
import by.grsu.skydiving.application.domain.model.skydiver.SportTitle;

public record ExternalSkydiverRequest(
    String firstName,
    String secondName,
    String patronymic,
    Gender gender,
    SportRank sportRank,
    SportTitle sportTitle
) {
}
