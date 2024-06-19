package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.trick.TrickSerieInfoForUpdate;

public interface GetTrickSerieByIdUseCase {
    TrickSerieInfoForUpdate getById(long trickSerieId);
}
