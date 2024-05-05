package by.grsu.skydiving.application.domain.model.skydiver;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
public record Skydiver (
    long id,
    FullName fullName,
    Gender gender,
    LocalDate dateOfBirth,
    Place placeOfBirth,
    PhoneNumber phoneNumber,
    FullName couchName,
    Height height,
    Weight weight,
    ClothingSize clothingSize,
    SportCareer sportCareer){
}

