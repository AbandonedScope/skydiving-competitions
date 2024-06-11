package by.grsu.skydiving.application.port.out;

public interface DeleteRefereeFromCompetitionStagePort {
    int deleteRefereeFromCompetitionByCompetitionCollegiumId(Long competitionStageId, Long refereeId);
}
