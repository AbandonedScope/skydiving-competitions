package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;
import by.grsu.skydiving.application.port.in.CreateCompetitionJumpingUseCase;
import by.grsu.skydiving.application.port.in.GetCompetitionMemberByMemberNumberPort;
import by.grsu.skydiving.application.port.in.GetUpdatableCompetitionUseCase;
import by.grsu.skydiving.application.port.out.CreateCompetitionJumpingPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateCompetitionJumpingService implements CreateCompetitionJumpingUseCase {
    private final GetUpdatableCompetitionUseCase getUpdatableCompetitionUseCase;
    private final GetCompetitionMemberByMemberNumberPort getCompetitionMemberByMemberNumberPort;
    private final CreateCompetitionJumpingPort createJumpingPort;

    @Override
    public void create(CreateCompetitionJumpingCommand command) {
        long competitionId = command.competitionId();
        int memberNumber = command.memberNumber();
        getUpdatableCompetitionUseCase.getCompetitionThatCanBeUpdated(competitionId);
        var competitionMember =
            getCompetitionMemberByMemberNumberPort.getByCompetitionIdAndMemberNumber(competitionId, memberNumber)
                .orElseThrow();

        JumpingInfo jumpingInfo = JumpingInfo.builder()
            .competitionMemberDetailId(competitionMember.id())
            .skydiverId(competitionMember.skydiverId())
            .performanceDate(command.performanceDate())
            .accuracy(command.accuracy())
            .number(command.jumpingNumber())
            .build();

        createJumpingPort.create(jumpingInfo);
    }
}
