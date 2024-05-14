package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.competition.Team;

public interface AddTeamToCompetitionUseCase {
    Team addTeam(AddTeamToCompetitionCommand command);

    record AddTeamToCompetitionCommand(
            long competitionId,
            Team team
    ) {
    }
}
