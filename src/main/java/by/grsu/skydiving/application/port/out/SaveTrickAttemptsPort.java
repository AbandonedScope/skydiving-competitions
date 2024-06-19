package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.trick.TrickAttempt;
import by.grsu.skydiving.application.domain.model.trick.TrickAttemptsIncome;
import java.util.List;

public interface SaveTrickAttemptsPort {
    List<TrickAttempt> saveAll(TrickAttemptsIncome trickAttempts);
}
