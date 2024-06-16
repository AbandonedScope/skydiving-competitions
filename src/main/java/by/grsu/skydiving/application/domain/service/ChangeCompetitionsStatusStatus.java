package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.port.in.ChangeCompetitionsStatusUseCase;
import by.grsu.skydiving.application.port.out.UpdateCompetitionsStatusesPort;
import by.grsu.skydiving.common.UseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ChangeCompetitionsStatusStatus implements ChangeCompetitionsStatusUseCase {
    private final UpdateCompetitionsStatusesPort updateCompetitionsStatusesPort;

    @Override
    public List<Long> updateCompetitionsStatus() {
        return updateCompetitionsStatusesPort.updateCompetitionsStatus();
    }
}
