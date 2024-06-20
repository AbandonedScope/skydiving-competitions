package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.port.in.GetRefereeByIdUseCase;
import by.grsu.skydiving.application.port.in.UpdateRefereeUseCase;
import by.grsu.skydiving.application.port.out.SaveRefereePort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateRefereeService implements UpdateRefereeUseCase {
    private final GetRefereeByIdUseCase getRefereeByIdUseCase;
    private final SaveRefereePort saveRefereePort;

    @Override
    public Referee updateReferee(long refereeId, Referee referee) {
        getRefereeByIdUseCase.getRefereeById(refereeId);

        referee = referee.withId(refereeId);
        saveRefereePort.save(referee, false);

        return referee;
    }
}
