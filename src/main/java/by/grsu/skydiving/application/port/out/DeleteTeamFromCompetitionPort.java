package by.grsu.skydiving.application.port.out;

public interface DeleteTeamFromCompetitionPort {
    void delete(long competitionId, long teamId);
}

