package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.port.in.GetUpdatableCompetitionUseCase;
import by.grsu.skydiving.application.port.in.UpdateTeamInCompetitionUseCase;
import by.grsu.skydiving.application.port.out.SaveTeamPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateTeamInCompetitionService implements UpdateTeamInCompetitionUseCase {
    private final GetUpdatableCompetitionUseCase getCompetitionUseCase;
    private final SaveTeamPort saveTeamPort;

    @Override
    public Team updateTeam(UpdateTeamInCompetitionCommand command) {
        getCompetitionUseCase.getCompetitionThatCanBeUpdated(command.competitionId());

        Team updatedTeam = command.team().withId(command.teamId());
        return saveTeamPort.saveTeam(updatedTeam, command.competitionId());
    }
}
