package by.grsu.skydiving.application.domain.exception.business;

public class TryToUpdateImmutableCompetitionException extends BusinessException {
    public TryToUpdateImmutableCompetitionException(String errorMessage) {
        super(errorMessage);
    }
}
