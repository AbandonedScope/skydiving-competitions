package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;
import by.grsu.skydiving.application.port.in.CreateCompetitionJumpingUseCase;
import by.grsu.skydiving.application.port.in.GetCompetitionMemberByMemberNumberPort;
import by.grsu.skydiving.application.port.in.GetRefereeableCompetitionUseCase;
import by.grsu.skydiving.application.port.out.CreateCompetitionJumpingPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateCompetitionJumpingService implements CreateCompetitionJumpingUseCase {
    private final GetRefereeableCompetitionUseCase getCompetitionUseCase;
    private final GetCompetitionMemberByMemberNumberPort getCompetitionMemberByMemberNumberPort;
    private final CreateCompetitionJumpingPort createJumpingPort;

    @Override
    public void create(CreateCompetitionJumpingCommand command) {
        long competitionId = command.competitionId();
        int memberNumber = command.memberNumber();
        getCompetitionUseCase.getCompetitionThatCanBeRefereed(competitionId);
        var competitionMember =
            getCompetitionMemberByMemberNumberPort.getByCompetitionIdAndMemberNumber(competitionId, memberNumber)
                .orElseThrow();

        JumpingInfo jumpingInfo = JumpingInfo.builder()
            .competitionMemberDetailsId(competitionMember.id())
            .skydiverId(competitionMember.skydiverId())
            .performanceDate(command.performanceDate())
            .accuracy(command.accuracy())
            .number(command.jumpingNumber())
            .build();

        createJumpingPort.create(jumpingInfo);
    }
}
