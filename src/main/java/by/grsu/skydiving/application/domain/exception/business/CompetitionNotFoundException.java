package by.grsu.skydiving.application.domain.exception.business;

public class CompetitionNotFoundException extends BusinessException {
    public CompetitionNotFoundException(Long competitionId) {
        super("Соревнование с id '%d' не было найдено.".formatted(competitionId));
    }
}
