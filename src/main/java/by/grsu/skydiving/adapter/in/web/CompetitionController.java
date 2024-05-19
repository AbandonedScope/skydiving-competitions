package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.CompetitionMapper;
import by.grsu.skydiving.adapter.in.web.mapper.TeamMapper;
import by.grsu.skydiving.adapter.in.web.request.AddStageRequest;
import by.grsu.skydiving.adapter.in.web.request.InitiateCompetitionRequest;
import by.grsu.skydiving.adapter.in.web.request.TeamRequest;
import by.grsu.skydiving.adapter.in.web.response.AddStageResponse;
import by.grsu.skydiving.adapter.in.web.response.InitiateCompetitionResponse;
import by.grsu.skydiving.adapter.in.web.response.TeamResponse;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionStage;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.port.in.AddStageToCompetitionUseCase;
import by.grsu.skydiving.application.port.in.AddStageToCompetitionUseCase.AddStageCommand;
import by.grsu.skydiving.application.port.in.AddTeamToCompetitionUseCase;
import by.grsu.skydiving.application.port.in.AddTeamToCompetitionUseCase.AddTeamToCompetitionCommand;
import by.grsu.skydiving.application.port.in.InitiateCompetitionUseCase;
import by.grsu.skydiving.application.port.in.InitiateCompetitionUseCase.InitiateCompetitionCommand;
import by.grsu.skydiving.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("api/v1/competitions")
@RequiredArgsConstructor
public class CompetitionController {
    private final InitiateCompetitionUseCase initiateUseCase;
    private final AddStageToCompetitionUseCase addStageUseCase;
    private final AddTeamToCompetitionUseCase addTeamUseCase;
    private final CompetitionMapper competitionMapper;
    private final TeamMapper teamMapper;

    @PostMapping("/initial")
    @ResponseStatus(HttpStatus.CREATED)
    public InitiateCompetitionResponse initiateCompetitionCreation(@RequestBody
                                                                   InitiateCompetitionRequest request) {
        InitiateCompetitionCommand command = competitionMapper.toCommand(request);
        Competition competition = initiateUseCase.initiateCompetition(command);

        return new InitiateCompetitionResponse(competition.getId());
    }

    @PostMapping("/{competitionId}/stage")
    @ResponseStatus(HttpStatus.CREATED)
    public AddStageResponse addStageToCompetition(
            @PathVariable
            Long competitionId,
            @RequestBody
            AddStageRequest request
    ) {
        AddStageCommand command = competitionMapper.toCommand(competitionId, request);
        CompetitionStage stage = addStageUseCase.addStage(command);

        return new AddStageResponse(competitionId, stage.id());
    }

    @PostMapping("/{competitionId}/team")
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponse addTeamToCompetition(
            @PathVariable
            Long competitionId,
            @RequestBody
            TeamRequest request
    ) {
        AddTeamToCompetitionCommand command = teamMapper.toCommand(competitionId, request);
        Team team = addTeamUseCase.addTeam(command);

        return new TeamResponse(team.id());
    }


}
