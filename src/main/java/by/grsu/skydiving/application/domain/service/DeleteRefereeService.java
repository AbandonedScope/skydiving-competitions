package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.RefereeNotFoundException;
import by.grsu.skydiving.application.port.in.DeleteRefereeUseCase;
import by.grsu.skydiving.application.port.out.DeleteRefereePort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteRefereeService implements DeleteRefereeUseCase {
    private final DeleteRefereePort deleteRefereePort;

    @Override
    public void deleteRefereeByRefereeId(Long refereeId) {
        int affectedRows = deleteRefereePort.deleteRefereeByRefereeId(refereeId);
        if(affectedRows == 0){
            throw new RefereeNotFoundException(refereeId);
        }
    }
}
