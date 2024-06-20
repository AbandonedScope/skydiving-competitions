package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.trick.TrickAttemptsUpdate;

public interface UpdateTrickAttemptsPort {
    TrickAttemptsUpdate updateTrickAttempts(TrickAttemptsUpdate attempts);
}
