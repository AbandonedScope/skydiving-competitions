package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.TeamMapper;
import by.grsu.skydiving.adapter.in.web.request.ExchangeTeamMemberWithIndividualRequest;
import by.grsu.skydiving.adapter.in.web.request.TeamRequest;
import by.grsu.skydiving.adapter.in.web.response.MembersOfCompetitionResponse;
import by.grsu.skydiving.adapter.in.web.response.TeamResponse;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.port.in.AddTeamToCompetitionUseCase;
import by.grsu.skydiving.application.port.in.AddTeamToCompetitionUseCase.AddTeamToCompetitionCommand;
import by.grsu.skydiving.application.port.in.DeleteTeamFromCompetitionUseCase;
import by.grsu.skydiving.application.port.in.ExchangeTeamMemberWithIndividualUseCase;
import by.grsu.skydiving.application.port.in.GetMembersOfCompetitionUseCase;
import by.grsu.skydiving.application.port.in.UpdateTeamInCompetitionUseCase;
import by.grsu.skydiving.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("api/v1/teams")
@RequiredArgsConstructor
public class TeamController {
    private final AddTeamToCompetitionUseCase addTeamUseCase;
    private final GetMembersOfCompetitionUseCase getMembersOfCompetitionUseCase;
    private final UpdateTeamInCompetitionUseCase updateTeamInCompetitionUseCase;
    private final ExchangeTeamMemberWithIndividualUseCase exchangeTeamMemberWithIndividualUseCase;
    private final DeleteTeamFromCompetitionUseCase deleteTeamFromCompetitionUseCase;
    private final TeamMapper mapper;

    @PostMapping("/competition/{competitionId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponse addTeamToCompetition(
        @PathVariable
        Long competitionId,
        @RequestBody
        TeamRequest request
    ) {
        AddTeamToCompetitionCommand command = mapper.toAddCommand(competitionId, request);
        Team team = addTeamUseCase.addTeam(command);

        return new TeamResponse(team.id());
    }

    @GetMapping("/{competitionId}/members")
    public MembersOfCompetitionResponse getMembersOfCompetition(@PathVariable long competitionId) {
        var members = getMembersOfCompetitionUseCase.getMembersOfCompetition(competitionId);

        return mapper.toResponse(competitionId, members);
    }

    @PutMapping("/{teamId}/competition/{competitionId}")
    public TeamResponse updateTeamInCompetition(
        @PathVariable
        Long competitionId,
        @PathVariable
        Long teamId,
        @RequestBody
        TeamRequest request
    ) {
        var updateTeamInCompetitionCommand = mapper.toUpdateCommand(competitionId, teamId, request);

        Team team = updateTeamInCompetitionUseCase.updateTeam(updateTeamInCompetitionCommand);
        return new TeamResponse(team.id());
    }

    @DeleteMapping("/{teamId}/competition/{competitionId}")
    public void deleteTeamInCompetition(
        @PathVariable
        Long competitionId,
        @PathVariable
        Long teamId
    ) {
        deleteTeamFromCompetitionUseCase.delete(competitionId, teamId);
    }

    @PutMapping("/{teamId}/competition/{competitionId}/exchange")
    public void exchangeTeamMemberWithIndividual(
        @PathVariable
        Long competitionId,
        @PathVariable
        Long teamId,
        @RequestBody
        ExchangeTeamMemberWithIndividualRequest request
    ) {
        var command = mapper.toExchangeCommand(competitionId, teamId, request);
        exchangeTeamMemberWithIndividualUseCase.exchange(command);
    }
}
