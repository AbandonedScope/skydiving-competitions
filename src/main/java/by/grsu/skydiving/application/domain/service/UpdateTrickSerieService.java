package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.trick.TrickSerieInfoForUpdate;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieInfoForUpdate.TrickSerieInfoForUpdateBuilder;
import by.grsu.skydiving.application.port.in.GetTrickSerieByIdUseCase;
import by.grsu.skydiving.application.port.in.UpdateTrickSerieUseCase;
import by.grsu.skydiving.application.port.out.UpdateTrickSeriePort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateTrickSerieService implements UpdateTrickSerieUseCase {
    private final GetTrickSerieByIdUseCase getTrickSerieByIdUseCase;
    private final UpdateTrickSeriePort updateTrickSeriePort;

    @Override
    public TrickSerieInfoForUpdate updateTrickSerie(UpdateTrickSerieCommand command) {
        TrickSerieInfoForUpdate trickSerie = getTrickSerieByIdUseCase.getById(command.trickSerieId());

        TrickSerieInfoForUpdateBuilder builder = trickSerie.toBuilder()
            .trickSerieId(command.trickSerieId())
            .isTimeSubmitted(command.isTimeSubmitted());

        Float timeWithoutPenalty;
        if (command.timeWithoutPenalty() == null) {
            timeWithoutPenalty = trickSerie.timeWithoutPenalty();
        } else if (command.timeWithoutPenalty() == 0) {
            timeWithoutPenalty = null;
        } else {
            timeWithoutPenalty = command.timeWithoutPenalty() / 1000;
            if (timeWithoutPenalty >= 16.00) {
                timeWithoutPenalty = 16.00f;
            }
        }

        builder.timeWithoutPenalty(timeWithoutPenalty);

        TrickSerieInfoForUpdate trickSerieInfoForUpdate = builder.build();

        return updateTrickSeriePort.updateTrickSerie(trickSerieInfoForUpdate);
    }
}
