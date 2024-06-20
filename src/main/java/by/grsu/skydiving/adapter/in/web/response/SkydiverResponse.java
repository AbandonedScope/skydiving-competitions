package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.skydiver.Gender;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record SkydiverResponse(
    int id,
    FullNameResponse name,
    LocalDate birthDate,
    String placeOfBirth,
    String placeOfWork,
    String phoneNumber,
    String education,
    Gender gender,
    String couchName,
    SportCareerResponse sportCareer,
    PassportDetailsResponse passport
) {
}
