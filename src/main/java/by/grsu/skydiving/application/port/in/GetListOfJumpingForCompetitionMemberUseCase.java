package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.jumping.CompetitionMemberJumping;

public interface GetListOfJumpingForCompetitionMemberUseCase {
    CompetitionMemberJumping getListOfJumping(long competitionId, long skydiverId);
}
