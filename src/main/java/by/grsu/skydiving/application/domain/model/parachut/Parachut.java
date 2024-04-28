package by.grsu.skydiving.application.domain.model.parachut;

import java.time.LocalDate;

public record Parachut(
        Integer parachutId,
        Short parachutNumber,
        LocalDate issueDate,
        Short jumpingAmount,
        ParachutType parachutType
) {
}
