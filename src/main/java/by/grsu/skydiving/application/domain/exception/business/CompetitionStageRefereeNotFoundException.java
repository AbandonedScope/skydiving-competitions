package by.grsu.skydiving.application.domain.exception.business;

public class CompetitionStageRefereeNotFoundException extends BusinessException {
    public CompetitionStageRefereeNotFoundException(Long refereeId, Long competitionStageId) {
        super("Referee with refereeId '%d' from competitionStage with competitionStageId '%d'  was not found".formatted(refereeId, competitionStageId));
    }
}
