package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.CompetitionMapper;
import by.grsu.skydiving.adapter.in.web.mapper.TeamMapper;
import by.grsu.skydiving.adapter.in.web.request.AddStageRequest;
import by.grsu.skydiving.adapter.in.web.request.InitiateCompetitionRequest;
import by.grsu.skydiving.adapter.in.web.request.TeamRequest;
import by.grsu.skydiving.adapter.in.web.request.UpdateCompetitionRequest;
import by.grsu.skydiving.adapter.in.web.response.AddStageResponse;
import by.grsu.skydiving.adapter.in.web.response.CompetitionShortInfoResponse;
import by.grsu.skydiving.adapter.in.web.response.InitiateCompetitionResponse;
import by.grsu.skydiving.adapter.in.web.response.PageResponse;
import by.grsu.skydiving.adapter.in.web.response.TeamResponse;
import by.grsu.skydiving.application.domain.model.common.FilterQuery;
import by.grsu.skydiving.application.domain.model.common.GetPageQuery;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionStage;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.port.in.AddStageToCompetitionUseCase;
import by.grsu.skydiving.application.port.in.AddStageToCompetitionUseCase.AddStageCommand;
import by.grsu.skydiving.application.port.in.AddTeamToCompetitionUseCase;
import by.grsu.skydiving.application.port.in.AddTeamToCompetitionUseCase.AddTeamToCompetitionCommand;
import by.grsu.skydiving.application.port.in.DeleteCompetitionUseCase;
import by.grsu.skydiving.application.port.in.GetCompetitionPageUseCase;
import by.grsu.skydiving.application.port.in.GetCompetitionPageUseCase.CompetitionFilterQuery;
import by.grsu.skydiving.application.port.in.InitiateCompetitionUseCase;
import by.grsu.skydiving.application.port.in.InitiateCompetitionUseCase.InitiateCompetitionCommand;
import by.grsu.skydiving.application.port.in.UpdateTeamInCompetitionUseCase;
import by.grsu.skydiving.application.port.out.UpdateCompetitionUseCase;
import by.grsu.skydiving.application.port.out.UpdateCompetitionUseCase.UpdateCompetitionCommand;
import by.grsu.skydiving.common.WebAdapter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("api/v1/competitions")
@RequiredArgsConstructor
public class CompetitionController {
    private final InitiateCompetitionUseCase initiateUseCase;
    private final AddStageToCompetitionUseCase addStageUseCase;
    private final AddTeamToCompetitionUseCase addTeamUseCase;
    private final GetCompetitionPageUseCase pageUseCase;
    private final UpdateCompetitionUseCase updateCompetitionUseCase;
    private final DeleteCompetitionUseCase deleteCompetitionUseCase;
    private final UpdateTeamInCompetitionUseCase updateTeamInCompetitionUseCase;
    private final CompetitionMapper competitionMapper;
    private final TeamMapper teamMapper;

    @GetMapping
    public PageResponse<CompetitionShortInfoResponse> getActiveAndLastCompetitions(
        @RequestParam
        long number,
        @RequestParam
        int size,
        @RequestParam(required = false)
        Boolean isCompleted
    ) {
        Map<String, Object> filters = HashMap.newHashMap(3);
        filters.put("isCompleted", isCompleted);
        filters.values().removeIf(Objects::isNull);

        FilterQuery filterQuery = new FilterQuery(filters);
        var getPageQuery = GetPageQuery.<CompetitionFilterQuery>builder()
            .pageNumber(number)
            .pageSize(size)
            .filterQuery(filterQuery)
            .build();

        var domainPage = pageUseCase.getPage(getPageQuery);
        return competitionMapper.toShortResponse(domainPage);
    }


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
        AddTeamToCompetitionCommand command = teamMapper.toAddCommand(competitionId, request);
        Team team = addTeamUseCase.addTeam(command);

        return new TeamResponse(team.id());
    }

    @PutMapping("/{competitionId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CompetitionShortInfoResponse updateCompetition(@PathVariable
                                                          Long competitionId,
                                                          @RequestBody
                                                          UpdateCompetitionRequest request) {
        UpdateCompetitionCommand command = competitionMapper.toCommand(competitionId, request);

        Competition competition = updateCompetitionUseCase.updateCompetition(command);

        return competitionMapper.toShortResponse(competition);
    }

    @PutMapping("/{competitionId}/team/{teamId}")
    public TeamResponse updateTeamInCompetition(
        @PathVariable
        Long competitionId,
        @PathVariable
        Long teamId,
        @RequestBody
        TeamRequest request
    ) {
        var updateTeamInCompetitionCommand = teamMapper.toUpdateCommand(competitionId, teamId, request);

        Team team = updateTeamInCompetitionUseCase.updateTeam(updateTeamInCompetitionCommand);
        return new TeamResponse(team.id());
    }

    @DeleteMapping("/{competitionId}")
    public void deleteCompetition(@PathVariable Long competitionId) {
        deleteCompetitionUseCase.deleteCompetition(competitionId);
    }
}
