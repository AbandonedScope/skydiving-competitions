package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.trick.TrickSerieInfoForUpdate;

public interface UpdateTrickSeriePort {
    TrickSerieInfoForUpdate updateTrickSerie(TrickSerieInfoForUpdate trickSerieInfoForUpdate);
}
