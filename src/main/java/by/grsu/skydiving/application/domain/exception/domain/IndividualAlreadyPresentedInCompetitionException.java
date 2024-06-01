package by.grsu.skydiving.application.domain.exception.domain;

public class IndividualAlreadyPresentedInCompetitionException extends DomainException {
    public IndividualAlreadyPresentedInCompetitionException(Long skydiverId, String competitionName) {
        super("Skydiver with id '%d' is already participating in competition with name '%s'."
            .formatted(skydiverId, competitionName));
    }
}
