package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.competition.Referee;
import java.util.Optional;

public interface GetRefereeByIdPort {
    Optional<Referee> getById(long refereeId);
}
