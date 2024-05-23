package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.port.in.DeleteCompetitionUseCase;
import by.grsu.skydiving.application.port.out.SoftDeleteCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteCompetitionService implements DeleteCompetitionUseCase {
    private final SoftDeleteCompetitionPort softDeleteCompetitionPort;

    @Override
    public void deleteCompetition(Long competitionId) {
        softDeleteCompetitionPort.deleteCompetition(competitionId);
    }
}
