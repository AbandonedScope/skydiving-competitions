package by.grsu.skydiving.application.domain.exception.domain;

public class TeamSizeLimitExceededException extends DomainException {

    public TeamSizeLimitExceededException(int limit) {
        super("Team. Failed adding skydiver. Max team size(%d) is reached.".formatted(limit));
    }
}
