package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.TryToUpdateImmutableCompetitionException;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.port.in.GetCompetitionUseCase;
import by.grsu.skydiving.application.port.in.UpdateTeamInCompetitionUseCase;
import by.grsu.skydiving.application.port.out.SaveTeamPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateTeamInCompetitionService implements UpdateTeamInCompetitionUseCase {
    private final GetCompetitionUseCase getCompetitionUseCase;
    private final SaveTeamPort saveTeamPort;

    @Override
    public Team updateTeam(UpdateTeamInCompetitionCommand command) {
        Competition competition = getCompetitionUseCase.getCompetition(command.competitionId());
        if (!competition.canBeUpdated()) {
            throw new TryToUpdateImmutableCompetitionException("Competition can't be updated");
        }

        Team updatedTeam = command.team().withId(command.teamId());
        return saveTeamPort.saveTeam(updatedTeam, command.competitionId());
    }
}
