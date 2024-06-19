package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.trick.TrickSerieInfoForUpdate;
import by.grsu.skydiving.application.port.in.UpdateTrickSerieUseCase;

public interface UpdateTrickSeriePort {
    TrickSerieInfoForUpdate updateTrickSerie (UpdateTrickSerieUseCase.UpdateTrickSerieCommand command);
}
