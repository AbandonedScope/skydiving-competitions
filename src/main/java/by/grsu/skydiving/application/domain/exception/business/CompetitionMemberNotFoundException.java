package by.grsu.skydiving.application.domain.exception.business;

public class CompetitionMemberNotFoundException
    extends BusinessException {
    public CompetitionMemberNotFoundException(long competitionId, long skydiverId) {
        super("Парашютист с id %d не участвует в соревновании с id %d."
            .formatted(skydiverId, competitionId));
    }
}
