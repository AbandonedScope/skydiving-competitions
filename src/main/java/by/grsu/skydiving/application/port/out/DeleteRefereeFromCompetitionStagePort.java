package by.grsu.skydiving.application.port.out;

public interface DeleteRefereeFromCompetitionStagePort {
    int deleteRefereeFromCompetitionByCompetitionStageId(Long competitionStageId, Long refereeId);
}
