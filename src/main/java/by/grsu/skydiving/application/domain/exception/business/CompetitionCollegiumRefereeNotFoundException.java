package by.grsu.skydiving.application.domain.exception.business;

public class CompetitionCollegiumRefereeNotFoundException extends BusinessException {
    public CompetitionCollegiumRefereeNotFoundException(Long refereeId, Long competitionCollegiumId) {
        super("Судья с id '%d' в судейской коллегии соревнования с id '%d' не был найден.".formatted(
            refereeId, competitionCollegiumId));
    }
}
