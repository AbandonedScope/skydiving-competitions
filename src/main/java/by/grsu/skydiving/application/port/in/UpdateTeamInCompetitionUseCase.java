package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.competition.Team;

public interface UpdateTeamInCompetitionUseCase {
    Team updateTeam(UpdateTeamInCompetitionCommand command);

    record UpdateTeamInCompetitionCommand(
       long competitionId,
       long teamId,
       Team team
    ) {
    }
}
