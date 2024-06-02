package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;
import by.grsu.skydiving.application.port.in.GetCompetitionJumpingUseCase;
import by.grsu.skydiving.application.port.in.GetUpdatableCompetitionUseCase;
import by.grsu.skydiving.application.port.in.UpdateCompetitionJumpingUseCase;
import by.grsu.skydiving.application.port.out.UpdateCompetitionJumpingPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateCompetitionJumpingService
    implements UpdateCompetitionJumpingUseCase {
    private final GetUpdatableCompetitionUseCase getUpdatableCompetitionUseCase;
    private final GetCompetitionJumpingUseCase getCompetitionJumpingUseCase;
    private final UpdateCompetitionJumpingPort updateCompetitionJumpingPort;

    @Override
    public JumpingInfo update(UpdateCompetitionJumpingCommand command) {
        long competitionId = command.competitionId();
        getUpdatableCompetitionUseCase.getCompetitionThatCanBeUpdated(competitionId);

        JumpingInfo jumpingInfo = getCompetitionJumpingUseCase.getJumpingInfo(command.id());

        jumpingInfo = updateJumping(jumpingInfo, command);

        return updateCompetitionJumpingPort.update(jumpingInfo);
    }

    private JumpingInfo updateJumping(JumpingInfo jumpingInfo, UpdateCompetitionJumpingCommand command) {
        return jumpingInfo.toBuilder()
            .accuracy(command.accuracy())
            .performanceDate(command.performanceDate())
            .build();
    }
}
