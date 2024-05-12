package by.grsu.skydiving.application.port.in;

public interface DeleteRefereeFromCompetitionStageUseCase {
    void deleteRefereeFromCompetitionByCompetitionStageId(Long competitionStageId, Long refereeId);
}
