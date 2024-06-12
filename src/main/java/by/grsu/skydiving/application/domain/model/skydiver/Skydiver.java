package by.grsu.skydiving.application.domain.model.skydiver;

import java.time.LocalDate;
import lombok.Builder;
import lombok.With;

@Builder
public record Skydiver(
    @With
    Long id,
    FullName name,
    Gender gender,
    LocalDate birthDate,
    Address placeOfBirth,
    Address placeOfWork,
    String education,
    PhoneNumber phoneNumber,
    FullName couchName,
    SportCareer sportCareer,
    Passport passport
) {
}

