package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.trick.TrickSerieTime;

public interface GetTimeWithoutPenaltyPort {
    TrickSerieTime getTrickSerieTime(Long trickSerieId);
}
