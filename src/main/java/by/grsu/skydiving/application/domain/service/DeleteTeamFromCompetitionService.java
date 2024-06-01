package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.port.in.DeleteTeamFromCompetitionUseCase;
import by.grsu.skydiving.application.port.in.GetUpdatableCompetitionUseCase;
import by.grsu.skydiving.application.port.out.DeleteTeamFromCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteTeamFromCompetitionService implements DeleteTeamFromCompetitionUseCase {
    private final GetUpdatableCompetitionUseCase getCompetitionUseCase;
    private final DeleteTeamFromCompetitionPort deleteTeamFromCompetitionPort;

    @Override
    public void delete(long competitionId, long teamId) {
        getCompetitionUseCase.getCompetitionThatCanBeUpdated(competitionId);

        deleteTeamFromCompetitionPort.deleteTeamFromCompetition(competitionId, teamId);
    }
}
