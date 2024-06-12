package by.grsu.skydiving.application.domain.model.jumping;

import java.time.LocalDate;
import lombok.Builder;

@Builder(toBuilder = true)
public record JumpingInfo(
    Long id,
    Long competitionMemberDetailsId,
    Long skydiverId,
    Integer accuracy,
    Integer number,
    LocalDate performanceDate
) {
}
