package by.grsu.skydiving.application.domain.model.skydiver;

import lombok.Builder;
import lombok.With;

import java.time.LocalDate;

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
        Height height,
        Weight weight,
        ClothingSize clothingSize,
        SportCareer sportCareer,
        Passport passport
) {
}

