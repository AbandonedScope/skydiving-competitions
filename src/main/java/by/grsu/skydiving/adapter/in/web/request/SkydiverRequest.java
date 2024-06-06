package by.grsu.skydiving.adapter.in.web.request;

import by.grsu.skydiving.application.domain.model.skydiver.Gender;
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
    PassportDetailsRequest passportDetails
) {
}
