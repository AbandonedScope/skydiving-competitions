package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.JumpingNotFoundException;
import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;
import by.grsu.skydiving.application.port.in.GetUpdatableCompetitionUseCase;
import by.grsu.skydiving.application.port.in.UpdateCompetitionJumpingUseCase;
import by.grsu.skydiving.application.port.out.GetCompetitionJumpingPort;
import by.grsu.skydiving.application.port.out.UpdateCompetitionJumpingPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateCompetitionJumpingService
    implements UpdateCompetitionJumpingUseCase {
    private final GetUpdatableCompetitionUseCase getUpdatableCompetitionUseCase;
    private final GetCompetitionJumpingPort getCompetitionJumpingPort;
    private final UpdateCompetitionJumpingPort updateCompetitionJumpingPort;

    @Override
    public JumpingInfo update(UpdateCompetitionJumpingCommand command) {
        long competitionId = command.competitionId();
        getUpdatableCompetitionUseCase.getCompetitionThatCanBeUpdated(competitionId);

        long jumpingId = command.id();
        JumpingInfo jumpingInfo = getCompetitionJumpingPort.getJumpingInfo(jumpingId)
            .orElseThrow(() -> new JumpingNotFoundException(jumpingId));

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
