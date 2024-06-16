package by.grsu.skydiving.application.domain.exception.business;

public class TryToUpdateImmutableCompetitionException extends BusinessException {
    public TryToUpdateImmutableCompetitionException() {
        super("Соревнование не может быть обновлено, так как уже проходит или завершено.");
    }
}
