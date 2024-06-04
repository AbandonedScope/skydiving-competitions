package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;
import by.grsu.skydiving.application.domain.model.jumping.NextJumpingNumber;
import by.grsu.skydiving.application.port.in.DeleteJumpingUseCase;
import by.grsu.skydiving.application.port.in.GetCompetitionJumpingUseCase;
import by.grsu.skydiving.application.port.in.GetUpdatableCompetitionUseCase;
import by.grsu.skydiving.application.port.out.DeleteJumpingPort;
import by.grsu.skydiving.application.port.out.GetNextNumberOfJumpingPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteJumpingService implements DeleteJumpingUseCase {
    private final GetUpdatableCompetitionUseCase getUpdatableCompetitionUseCase;
    private final GetCompetitionJumpingUseCase getCompetitionJumpingUseCase;
    private final GetNextNumberOfJumpingPort getNextNumberOfJumpingPort;
    private final DeleteJumpingPort deleteJumpingPort;

    @Override
    public void delete(long competitionId, long jumpingId) {
        getUpdatableCompetitionUseCase.getCompetitionThatCanBeUpdated(competitionId);
        JumpingInfo jumpingInfo = getCompetitionJumpingUseCase.getJumpingInfo(jumpingId);
        NextJumpingNumber nextNumber = getNextNumberOfJumpingPort.genNextNumberOfJumping(competitionId, jumpingInfo.skydiverId());
        if (!nextNumber.isLimitReached()
        && nextNumber.nextJumpingNumber() - jumpingInfo.number() != 1) {
            throw new CannotDeleteNotLastJumpingException();
        }

        deleteJumpingPort.deleteById(jumpingId);
    }
}
