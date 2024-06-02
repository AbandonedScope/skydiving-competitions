package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.CompetitionMemberNotFoundException;
import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import by.grsu.skydiving.application.domain.model.jumping.CompetitionMemberJumping;
import by.grsu.skydiving.application.port.in.GetListOfJumpingForCompetitionMemberUseCase;
import by.grsu.skydiving.application.port.out.GetCompetitionMemberPort;
import by.grsu.skydiving.application.port.out.GetListOfJumpingForCompetitionMemberPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetListOfJumpingForCompetitionMemberService
    implements GetListOfJumpingForCompetitionMemberUseCase {
    private final GetCompetitionMemberPort getCompetitionMemberPort;
    private final GetListOfJumpingForCompetitionMemberPort getListOfJumpingForCompetitionMemberPort;

    @Override
    public CompetitionMemberJumping getListOfJumping(long competitionId, long skydiverId) {
        CompetitionMember competitionMember =
            getCompetitionMemberPort.getByCompetitionIdAndSkydiverId(competitionId, skydiverId)
                .orElseThrow(() -> new CompetitionMemberNotFoundException(competitionId, skydiverId));

        long competitionMemberId = competitionMember.id();
        var jumping = getListOfJumpingForCompetitionMemberPort.getList(competitionMemberId);

        return new CompetitionMemberJumping(
            competitionMemberId,
            jumping
        );
    }
}
