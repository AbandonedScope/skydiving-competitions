package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickSerieShortInfo;

public interface GetTrickSerieShortInfoUseCse {
    TrickSerieShortInfo getTrickSerieShortInfoBeTrickSerieId(Long trickSerieId);
}
