package by.grsu.skydiving.application.domain.model.parachut;

import java.time.LocalDate;

public record Parachute(
        Integer parachuteId,
        Short parachuteNumber,
        LocalDate issueDate,
        Short jumpingAmount,
        ParachuteType parachuteType
) {
}
