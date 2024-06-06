package by.grsu.skydiving.application.domain.model.jumping;

import java.time.LocalDate;
import lombok.Builder;

@Builder(toBuilder = true)
public record JumpingInfo(
    Long id,
    Long competitionMemberDetailsId,
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
