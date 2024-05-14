package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.port.in.AddRefereeUseCase;
import by.grsu.skydiving.application.port.out.SaveRefereePort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddRefereeService implements AddRefereeUseCase {
    private final SaveRefereePort saveRefereePort;

    @Override
    public Long addReferee(Referee referee) {
        return saveRefereePort.save(referee);
    }
}
