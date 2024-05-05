package by.grsu.skydiving.application.domain.exception.business;

public class CompetitionNotFoundException extends BusinessException {
    public CompetitionNotFoundException(Long competitionId) {
        super("Competition with id '%d' was not found.".formatted(competitionId));
    }
}
