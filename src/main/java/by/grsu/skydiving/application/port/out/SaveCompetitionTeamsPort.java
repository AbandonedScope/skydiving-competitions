package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.Team;

import java.util.List;

public interface SaveCompetitionTeamsPort {
    List<Team> saveTeams(Competition competition);
}
