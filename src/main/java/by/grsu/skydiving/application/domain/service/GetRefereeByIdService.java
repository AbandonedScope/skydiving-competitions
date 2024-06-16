package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.RefereeNotFoundException;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.port.in.GetRefereeByIdUseCase;
import by.grsu.skydiving.application.port.out.GetRefereeByIdPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetRefereeByIdService implements GetRefereeByIdUseCase {
    private final GetRefereeByIdPort getRefereeByIdPort;

    @Override
    public Referee getRefereeById(long refereeId) {
        return getRefereeByIdPort.getById(refereeId)
            .orElseThrow(() -> new RefereeNotFoundException(refereeId));
    }
}
