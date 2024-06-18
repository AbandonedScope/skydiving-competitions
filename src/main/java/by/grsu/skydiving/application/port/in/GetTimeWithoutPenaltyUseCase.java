package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickSerieTime;

public interface GetTimeWithoutPenaltyUseCase {
    TrickSerieTime getTrickSerieTime(Long trickSerieId);
}
