package by.grsu.skydiving.application.port.in;

public interface DeleteTeamFromCompetitionUseCase {
    void delete(long competitionId, long teamId);
}
