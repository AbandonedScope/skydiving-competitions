package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.trick.TrickSerieInfoForUpdate;
import by.grsu.skydiving.application.port.in.UpdateTrickSerieUseCase;
import by.grsu.skydiving.application.port.out.UpdateTrickSeriePort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateTrickSerieService implements UpdateTrickSerieUseCase {
    private final UpdateTrickSeriePort updateTrickSeriePort;

    @Override
    public TrickSerieInfoForUpdate updateTrickSerie(UpdateTrickSerieCommand command) {
        return updateTrickSeriePort.updateTrickSerie(command);
    }
}
