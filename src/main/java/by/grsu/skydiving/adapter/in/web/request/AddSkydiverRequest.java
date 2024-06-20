package by.grsu.skydiving.adapter.in.web.request;

import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import by.grsu.skydiving.application.domain.model.skydiver.Gender;
import by.grsu.skydiving.application.domain.model.skydiver.SportRank;
import java.time.LocalDate;

public record AddSkydiverRequest(
    String firstName,
    String secondName,
    String patronymic,
    Gender gender,
    LocalDate birthDate,
    String placeOfBirth,
    String placeOfWork,
    String education,
    String phone,
    FullName couchName,
    LocalDate beginDateOfSportCareer,
    SportRank sportRank,
    PassportDetailsRequest passportDetails
) {
}
