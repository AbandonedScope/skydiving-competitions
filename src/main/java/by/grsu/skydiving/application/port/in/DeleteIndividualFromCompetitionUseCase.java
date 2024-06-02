package by.grsu.skydiving.application.port.in;

public interface DeleteIndividualFromCompetitionUseCase {
    void delete(long competitionId, long individualId);
}
