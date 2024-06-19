package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.trick.TrickSerieShortInfo;

public interface GetTrickSerieShortInfoUseCse {
    TrickSerieShortInfo getTrickSerieShortInfoBeTrickSerieId(Long trickSerieId);
}
