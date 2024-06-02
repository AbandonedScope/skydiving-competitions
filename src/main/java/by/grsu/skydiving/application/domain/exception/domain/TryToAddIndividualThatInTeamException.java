package by.grsu.skydiving.application.domain.exception.domain;

public class TryToAddIndividualThatInTeamException extends DomainException {
    public TryToAddIndividualThatInTeamException(Long skydiverId, String teamName, String competitionName) {
        super("Skydiver with id '%d' is already participating in competition with name '%s' as member of team '%s'."
            .formatted(skydiverId, competitionName, teamName));
    }
}
