package by.grsu.skydiving.adapter.in.web.request;

import by.grsu.skydiving.application.domain.model.skydiver.Gender;
import by.grsu.skydiving.application.domain.model.skydiver.SportRank;

public record UpdateExternalSkydiverRequest(
    String firstName,
    String secondName,
    String patronymic,
    Gender gender,
    SportRank sportRank
) {
}
