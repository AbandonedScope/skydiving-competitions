package by.grsu.skydiving.application.port.in;

public interface DeleteRefereeFromCompetitionStageUseCase {
    void deleteRefereeFromCompetitionByCompetitionCollegiumId(Long competitionStageId, Long refereeId);
}
