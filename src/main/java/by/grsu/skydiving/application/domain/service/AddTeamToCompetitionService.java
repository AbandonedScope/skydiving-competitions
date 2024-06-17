package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.TeamWithNameAlreadyExistsException;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.port.in.AddTeamToCompetitionUseCase;
import by.grsu.skydiving.application.port.in.GetUpdatableCompetitionUseCase;
import by.grsu.skydiving.application.port.out.ExistsTeamByNamePort;
import by.grsu.skydiving.application.port.out.SaveCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddTeamToCompetitionService implements AddTeamToCompetitionUseCase {
    private final GetUpdatableCompetitionUseCase getCompetitionUseCase;
    private final ExistsTeamByNamePort existsTeamByNamePortPort;
    private final SaveCompetitionPort saveCompetitionPort;

    @Override
    public Team addTeam(AddTeamToCompetitionCommand command) {
        long competitionId = command.competitionId();
        Competition competition = getCompetitionUseCase.getCompetitionThatCanBeUpdated(competitionId);

        Team team = command.team();
        String teamName = team.name();
        if (existsTeamByNamePortPort.exists(teamName)) {
            throw new TeamWithNameAlreadyExistsException(teamName);
        }

        competition.addTeam(team);
        competition = saveCompetitionPort.save(competition);
        return competition.getTeamByName(teamName);
    }
}
