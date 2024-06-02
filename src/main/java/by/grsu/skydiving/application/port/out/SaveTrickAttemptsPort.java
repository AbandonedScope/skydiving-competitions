package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickAttempt;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickAttemptsIncome;

import java.util.List;

public interface SaveTrickAttemptsPort {
    List<TrickAttempt> saveAll(TrickAttemptsIncome trickAttempts);
}
