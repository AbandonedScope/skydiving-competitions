package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.JumpingNotFoundException;
import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;
import by.grsu.skydiving.application.port.in.GetCompetitionJumpingUseCase;
import by.grsu.skydiving.application.port.out.GetCompetitionJumpingPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetCompetitionJumpingService implements GetCompetitionJumpingUseCase {
    private final GetCompetitionJumpingPort getCompetitionJumpingPort;

    @Override
    public JumpingInfo getJumpingInfo(long jumpingId) {
        return getCompetitionJumpingPort.getJumpingInfo(jumpingId)
            .orElseThrow(() -> new JumpingNotFoundException(jumpingId));
    }
}
