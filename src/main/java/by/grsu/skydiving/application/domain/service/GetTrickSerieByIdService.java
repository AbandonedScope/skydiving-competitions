package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.TrickSeieNotFoundException;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieInfoForUpdate;
import by.grsu.skydiving.application.port.in.GetTrickSerieByIdUseCase;
import by.grsu.skydiving.application.port.out.FindTrickSerieForUpdateByIdPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetTrickSerieByIdService implements GetTrickSerieByIdUseCase {
    private final FindTrickSerieForUpdateByIdPort findTrickSerieForUpdateByIdPort;

    @Override
    public TrickSerieInfoForUpdate getById(long trickSerieId) {
        return findTrickSerieForUpdateByIdPort.findById(trickSerieId)
            .orElseThrow(TrickSeieNotFoundException::new);
    }
}
