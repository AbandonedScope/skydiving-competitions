package by.grsu.skydiving.application.domain.exception.domain;

public class CollegiumRefereeUpdateException
    extends DomainException {
    public CollegiumRefereeUpdateException() {
        super("Попытка обновления судейской коллегии провалилось, т.к. она не принадлежит соревнованию.");
    }
}
