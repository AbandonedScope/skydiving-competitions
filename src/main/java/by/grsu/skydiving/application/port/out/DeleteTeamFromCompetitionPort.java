package by.grsu.skydiving.application.port.out;

public interface DeleteTeamFromCompetitionPort {
    void deleteTeamFromCompetition(long competitionId, long teamId);
}

