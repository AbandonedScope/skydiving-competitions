package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.skydiver.*;
import lombok.Builder;

import java.time.LocalDate;

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
        FullNameResponse couchName,
        Integer height,
        Integer weight,
        ClothingSizeResponse clothingSize,
        SportCareerResponse sportCareer
) {
}
