package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.CompetitionNotFoundException;
import by.grsu.skydiving.application.port.in.CheckExistsCompetitionUseCase;
import by.grsu.skydiving.application.port.out.ExistsCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CheckExistsCompetitionService implements CheckExistsCompetitionUseCase {
    private final ExistsCompetitionPort existsCompetitionPort;

    @Override
    public void checkExists(long competitionId) {
        if (!existsCompetitionPort.existsById(competitionId)) {
            throw new CompetitionNotFoundException(competitionId);
        }
    }
}
