package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickSerieShortInfo;

public interface GetTrickSerieShortInfoPort {
    TrickSerieShortInfo getTrickSerieShortInfoBeTrickSerieId(Long trickSerieId);
}
