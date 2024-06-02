package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;

public interface UpdateCompetitionJumpingPort {
    JumpingInfo update(JumpingInfo jumpingInfo);
}
