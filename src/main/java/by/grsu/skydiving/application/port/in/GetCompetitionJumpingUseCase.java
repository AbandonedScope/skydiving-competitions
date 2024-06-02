package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;

public interface GetCompetitionJumpingUseCase {
    JumpingInfo getJumpingInfo(long jumpingId);
}
