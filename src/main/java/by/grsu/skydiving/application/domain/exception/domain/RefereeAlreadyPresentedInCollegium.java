package by.grsu.skydiving.application.domain.exception.domain;

public class RefereeAlreadyPresentedInCollegium extends DomainException {
    public RefereeAlreadyPresentedInCollegium(long refereeId) {
        super("Ошибка при добавлении судьи в коллегию. Судья с id '%d' уже состоит в коллегии."
            .formatted(refereeId));
    }
}
