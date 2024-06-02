package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;

public interface CreateCompetitionJumpingPort {
    void create(JumpingInfo jumping);
}
