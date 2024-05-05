package by.grsu.skydiving.application.domain.model.skydiver;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record Skydiver (
    Long id,
    FullName fullName,
    Gender gender,
    LocalDate dateOfBirth,
    Address placeOfBirth,
    PhoneNumber phoneNumber,
    FullName couchName,
    Height height,
    Weight weight,
    ClothingSize clothingSize,
    SportCareer sportCareer){
}

