package by.grsu.skydiving.application.domain.model.skydiver;

import java.time.LocalDate;
import lombok.Builder;
import lombok.With;

@Builder(toBuilder = true)
public record Skydiver(
    @With
    Long id,
    @With
    FullName name,
    Gender gender,
    LocalDate birthDate,
    Address placeOfBirth,
    Address placeOfWork,
    String education,
    PhoneNumber phoneNumber,
    String couchName,
    SportCareer sportCareer,
    @With
    Passport passport
) {
}

