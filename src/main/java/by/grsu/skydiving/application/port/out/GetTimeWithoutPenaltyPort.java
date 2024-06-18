package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickSerieTime;

public interface GetTimeWithoutPenaltyPort {
    TrickSerieTime getTrickSerieTime(Long trickSerieId);
}
