package by.grsu.skydiving.application.domain.model.jumping;

import java.time.LocalDate;

public record JumpingInfo(
        int jumpingInfoId,
        OverallJumpingInfo overallJumpingInfo,
        short exerciseNumber,
        LocalDate performanceDate,
        FlightCharacteristics flightCharacteristics,
        AviationUnit aviationUnit,
        AircraftType aircraftType
) {
}
