package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.TeamWithNameAlreadyExistsException;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.port.in.AddTeamToCompetitionUseCase;
import by.grsu.skydiving.application.port.in.GetUpdatableCompetitionUseCase;
import by.grsu.skydiving.application.port.out.ExistsTeamByNamePort;
import by.grsu.skydiving.application.port.out.GetNextMemberNumberAndIncrementPort;
import by.grsu.skydiving.application.port.out.SaveCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddTeamToCompetitionService implements AddTeamToCompetitionUseCase {
    private final GetUpdatableCompetitionUseCase getCompetitionUseCase;
    private final GetNextMemberNumberAndIncrementPort getNextMemberNumberAndIncrementPort;
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

        var members = team.members().stream()
            .map(member -> {
                int memberNumber = getNextMemberNumberAndIncrementPort.getAndIncrement(competitionId);

                return member.withMemberNumber(memberNumber);
            })
            .collect(Collectors.toSet());

        team = team.withMembers(members);
        competition.addTeam(team);
        competition = saveCompetitionPort.save(competition);
        return competition.getTeamByName(teamName);
    }
}
