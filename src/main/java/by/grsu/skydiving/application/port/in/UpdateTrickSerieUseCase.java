package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.trick.TrickSerieInfoForUpdate;

public interface UpdateTrickSerieUseCase {

    TrickSerieInfoForUpdate updateTrickSerie (UpdateTrickSerieCommand command);

    record UpdateTrickSerieCommand(
          Long trickSerieId,
          Boolean isTimeSubmitted,
          Float timeWithoutPenalty
    ) {
    }
}
