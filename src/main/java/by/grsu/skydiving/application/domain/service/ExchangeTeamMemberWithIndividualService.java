package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.TryToUpdateImmutableCompetitionException;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.port.in.ExchangeTeamMemberWithIndividualUseCase;
import by.grsu.skydiving.application.port.in.GetCompetitionUseCase;
import by.grsu.skydiving.application.port.out.UpdateCompetitionMemberPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ExchangeTeamMemberWithIndividualService implements ExchangeTeamMemberWithIndividualUseCase {
    private final GetCompetitionUseCase getCompetitionUseCase;
    private final UpdateCompetitionMemberPort updateCompetitionMemberPort;

    @Override
    public void exchange(ExchangeTeamMemberWithIndividualCommand command) {
        long competitionId = command.competitionId();
        long teamId = command.teamId();
        long teamMemberId = command.teamMemberId();
        long individualId = command.individualId();
        Competition competition = getCompetitionUseCase.getCompetition(competitionId);

        if (!competition.canMoveMembers()) {
            throw new TryToUpdateImmutableCompetitionException();
        }

        Team team = competition.getTeamById(teamId);
        CompetitionMember teamCompetitionMember = team.getById(teamMemberId);

        CompetitionMember individual = competition.getIndividualById(individualId);
        teamCompetitionMember = teamCompetitionMember.withTeamId(null);
        individual = individual.withTeamId(teamId);

        updateCompetitionMemberPort.update(teamCompetitionMember);
        updateCompetitionMemberPort.update(individual);
    }
}
