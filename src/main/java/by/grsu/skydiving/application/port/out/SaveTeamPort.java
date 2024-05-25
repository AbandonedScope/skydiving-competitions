package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.competition.Team;

public interface SaveTeamPort {
    Team saveTeam(Team team, long competitionId);
}
