package by.grsu.skydiving.application.domain.model;

import java.time.LocalDate;

public record JumpingInfo(
            int jumpingInfoId,
            short nextJumpNumber,
            short nextJumpNumberDuring,
            short jumpNumberBillable,
            short exerciseNumber,
            LocalDate performanceDate,
            short height,
            short speed,
            short timeDelayOfParachutOpening,
            String aviationUnit,
            AircraftType aircraftType
) {
}
