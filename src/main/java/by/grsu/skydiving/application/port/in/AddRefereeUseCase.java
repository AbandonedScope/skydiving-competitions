package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.competition.Referee;

public interface AddRefereeUseCase {
    Long addReferee(Referee referee);
}
