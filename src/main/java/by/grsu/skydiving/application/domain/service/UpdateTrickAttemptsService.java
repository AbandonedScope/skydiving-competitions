package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.trick.TrickAttemptsUpdate;
import by.grsu.skydiving.application.port.in.UpdateTrickAttemptsUseCase;
import by.grsu.skydiving.application.port.out.UpdateTrickAttemptsPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateTrickAttemptsService implements UpdateTrickAttemptsUseCase {
    private final UpdateTrickAttemptsPort updateTrickAttemptsPort;

    @Override
    public TrickAttemptsUpdate updateTrickAttempts(TrickAttemptsUpdate attempts) {
        return updateTrickAttemptsPort.updateTrickAttempts(attempts);
    }
}
