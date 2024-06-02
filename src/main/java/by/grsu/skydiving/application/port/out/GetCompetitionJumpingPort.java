package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;
import java.util.Optional;

public interface GetCompetitionJumpingPort {
    Optional<JumpingInfo> getJumpingInfo(long jumpingId);
}
