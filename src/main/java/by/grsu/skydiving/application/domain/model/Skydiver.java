package by.grsu.skydiving.application.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
@Builder
public class Skydiver {
    private final long id;
    private final FullName fullName;
    private final Gender gender;
    private final LocalDate dateOfBirth;
    private final Place placeOfBirth;
    private final PhoneNumber phoneNumber;
    private final FullName couchName;
    private final Height height;
    private final Weight weight;
    private final ClothingSize clothingSize;
}
