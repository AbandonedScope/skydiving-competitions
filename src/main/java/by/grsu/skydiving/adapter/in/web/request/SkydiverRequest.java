package by.grsu.skydiving.adapter.in.web.request;

import by.grsu.skydiving.application.domain.model.skydiver.Gender;
import by.grsu.skydiving.application.domain.model.skydiver.SportRank;
import by.grsu.skydiving.application.domain.model.skydiver.SportTitle;
import java.time.LocalDate;

public record SkydiverRequest(
    String firstName,
    String secondName,
    String patronymic,
    Gender gender,
    LocalDate birthDate,
    String placeOfBirth,
    String placeOfWork,
    String education,
    String phone,
    SportRank sportRank,
    SportTitle sportTitle,
    PassportDetailsRequest passportDetails
) {
}
