package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.competition.Referee;

public interface UpdateRefereeUseCase {
    Referee updateReferee(long refereeId, Referee referee);
}
