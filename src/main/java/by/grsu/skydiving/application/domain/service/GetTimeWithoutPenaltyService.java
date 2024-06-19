package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.trick.TrickSerieTime;
import by.grsu.skydiving.application.port.in.GetTimeWithoutPenaltyUseCase;
import by.grsu.skydiving.application.port.out.GetTimeWithoutPenaltyPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetTimeWithoutPenaltyService implements GetTimeWithoutPenaltyUseCase {
    private final GetTimeWithoutPenaltyPort timeWithoutPenaltyPort;

    @Override
    public TrickSerieTime getTrickSerieTime(Long trickSerieId) {
        return timeWithoutPenaltyPort.getTrickSerieTime(trickSerieId);
    }
}
