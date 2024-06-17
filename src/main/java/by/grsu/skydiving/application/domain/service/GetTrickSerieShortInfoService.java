package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickSerieShortInfo;
import by.grsu.skydiving.application.port.in.GetTrickSerieShortInfoUseCse;
import by.grsu.skydiving.application.port.out.GetTrickSerieShortInfoPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetTrickSerieShortInfoService implements GetTrickSerieShortInfoUseCse {
    private final GetTrickSerieShortInfoPort getTrickSerieShortInfoPort;

    @Override
    public TrickSerieShortInfo getTrickSerieShortInfoBeTrickSerieId(Long trickSerieId) {
        return getTrickSerieShortInfoPort.getTrickSerieShortInfoBeTrickSerieId(trickSerieId);
    }
}
