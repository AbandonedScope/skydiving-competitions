package by.grsu.skydiving.application.port.in;

public interface DeleteRefereeFromCompetitionCollegiumUseCase {
    void deleteByCollegiumId(Long competitionId, Long refereeId);
}
