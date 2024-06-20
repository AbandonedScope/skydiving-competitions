package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.trick.TrickAttemptsUpdate;

public interface UpdateTrickAttemptsUseCase {
    TrickAttemptsUpdate updateTrickAttempts(TrickAttemptsUpdate attempts);
}
