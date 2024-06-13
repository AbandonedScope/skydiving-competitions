package by.grsu.skydiving.application.domain.exception.business;

public class CompetitionCollegiumRefereeNotFoundException extends BusinessException {
    public CompetitionCollegiumRefereeNotFoundException(Long refereeId, Long competitionCollegiumId) {
        super("Referee with id '%d' from competition collegium with id '%d'  was not found".formatted(
            refereeId, competitionCollegiumId));
    }
}
