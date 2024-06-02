package by.grsu.skydiving.application.domain.model.jumping;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record JumpingInfo(
        Long jumpingInfoId,
        Long competitionMemberDetailId,
        Long skydiverId,
        OverallJumpingInfo overallJumpingInfo,
        Integer accuracy,
        Integer number,
        LocalDate performanceDate,
        FlightCharacteristics flightCharacteristics,
        AviationUnit aviationUnit,
        AircraftType aircraftType
) {
}
