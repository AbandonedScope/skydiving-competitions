package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.TryToUpdateImmutableCompetitionException;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.port.in.DeleteTeamFromCompetitionUseCase;
import by.grsu.skydiving.application.port.in.GetCompetitionUseCase;
import by.grsu.skydiving.application.port.out.DeleteTeamFromCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteTeamFromCompetitionService implements DeleteTeamFromCompetitionUseCase {
    private final GetCompetitionUseCase getCompetitionUseCase;
    private final DeleteTeamFromCompetitionPort deleteTeamFromCompetitionPort;

    @Override
    public void delete(long competitionId, long teamId) {
        Competition competition = getCompetitionUseCase.getCompetition(competitionId);
        if (!competition.canBeUpdated()) {
            throw new TryToUpdateImmutableCompetitionException("Competition can't be updated");
        }

        deleteTeamFromCompetitionPort.deleteTeamFromCompetition(competitionId, teamId);
    }
}
