package by.grsu.skydiving.application.port.out;

public interface DeleteRefereeFromCompetitionStagePort {
    void deleteRefereeFromCompetitionByCompetitionStageId(Long competitionStageId, Long refereeId);
}
