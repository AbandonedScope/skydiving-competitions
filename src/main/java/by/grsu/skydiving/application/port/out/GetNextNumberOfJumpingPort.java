package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.jumping.NextJumpingNumber;

public interface GetNextNumberOfJumpingPort {
    NextJumpingNumber genNextNumberOfJumping(long competitionId, long skydiverId);
}
