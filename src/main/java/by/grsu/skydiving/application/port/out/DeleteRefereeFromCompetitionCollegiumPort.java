package by.grsu.skydiving.application.port.out;

public interface DeleteRefereeFromCompetitionCollegiumPort {
    int deleteFromCompetition(Long competitionId, Long refereeId);
}
