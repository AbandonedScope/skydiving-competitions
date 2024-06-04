package by.grsu.skydiving.application.domain.model.jumping;

import java.util.List;
import lombok.Builder;

@Builder
public record CompetitionMemberJumping(
    long competitionMemberId,
    List<JumpingInfo> jumping
) {
}
