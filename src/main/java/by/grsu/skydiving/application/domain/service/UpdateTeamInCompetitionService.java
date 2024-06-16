package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.port.in.GetNextMemberNumberAndUpdateUseCase;
import by.grsu.skydiving.application.port.in.GetUpdatableCompetitionUseCase;
import by.grsu.skydiving.application.port.in.UpdateTeamInCompetitionUseCase;
import by.grsu.skydiving.application.port.out.SaveTeamPort;
import by.grsu.skydiving.common.UseCase;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateTeamInCompetitionService implements UpdateTeamInCompetitionUseCase {
    private final GetUpdatableCompetitionUseCase getCompetitionUseCase;
    private final GetNextMemberNumberAndUpdateUseCase getNextMemberNumberAndUpdateUseCase;
    private final SaveTeamPort saveTeamPort;

    @Override
    public Team updateTeam(UpdateTeamInCompetitionCommand command) {
        Competition competition = getCompetitionUseCase
            .getCompetitionThatCanBeUpdated(command.competitionId());

        Team toUpdate = competition.getTeamById(command.teamId());

        //todo
        Team updatedTeam = mapUpdated(toUpdate, command.team());
        return saveTeamPort.saveTeam(updatedTeam, command.competitionId());
    }

    private Team mapUpdated(Team toUpdate, Team newData) {
        Set<CompetitionMember> newMembers = new HashSet<>();

        for (CompetitionMember member : newData.members()) {
            Optional<CompetitionMember> optionalCompetitionMember = toUpdate.getById(member.skydiverId());
            if (optionalCompetitionMember.isEmpty()) {
                int memberNumber = getNextMemberNumberAndUpdateUseCase.getMemberMemberAndUpdate(member.competitionId());
                newMembers.add(member.withMemberNumber(memberNumber));
            } else {
                newMembers.add(optionalCompetitionMember.get());
            }
        }

        return toUpdate.toBuilder()
            .name(newData.name())
            .members(newMembers)
            .build();
    }
}
