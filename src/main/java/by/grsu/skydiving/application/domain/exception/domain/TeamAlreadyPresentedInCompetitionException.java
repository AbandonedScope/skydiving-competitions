package by.grsu.skydiving.application.domain.exception.domain;

public class TeamAlreadyPresentedInCompetitionException extends DomainException {
    public TeamAlreadyPresentedInCompetitionException(String teamName, String competitionName) {
        super("Team with name '%s' already presented in competition '%s'.".formatted(teamName, competitionName));
    }
}
