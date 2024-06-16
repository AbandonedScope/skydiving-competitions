package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.CollegiumMapper;
import by.grsu.skydiving.adapter.in.web.mapper.CompetitionMapper;
import by.grsu.skydiving.adapter.in.web.request.AddCollegiumRequest;
import by.grsu.skydiving.adapter.in.web.request.InitiateCompetitionRequest;
import by.grsu.skydiving.adapter.in.web.request.UpdateCollegiumRequest;
import by.grsu.skydiving.adapter.in.web.request.UpdateCompetitionRequest;
import by.grsu.skydiving.adapter.in.web.response.CollegiumResponse;
import by.grsu.skydiving.adapter.in.web.response.CompetitionResponse;
import by.grsu.skydiving.adapter.in.web.response.CompetitionShortInfoResponse;
import by.grsu.skydiving.adapter.in.web.response.InitiateCompetitionResponse;
import by.grsu.skydiving.adapter.in.web.response.PageResponse;
import by.grsu.skydiving.application.domain.model.common.FilterQuery;
import by.grsu.skydiving.application.domain.model.common.GetPageQuery;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionCollegium;
import by.grsu.skydiving.application.port.in.AddCollegiumToCompetitionUseCase;
import by.grsu.skydiving.application.port.in.AddCollegiumToCompetitionUseCase.AddCollegiumCommand;
import by.grsu.skydiving.application.port.in.DeleteCompetitionUseCase;
import by.grsu.skydiving.application.port.in.GetCompetitionPageUseCase;
import by.grsu.skydiving.application.port.in.GetCompetitionPageUseCase.CompetitionFilterQuery;
import by.grsu.skydiving.application.port.in.GetCompetitionUseCase;
import by.grsu.skydiving.application.port.in.InitiateCompetitionUseCase;
import by.grsu.skydiving.application.port.in.InitiateCompetitionUseCase.InitiateCompetitionCommand;
import by.grsu.skydiving.application.port.in.UpdateCollegiumOfCompetitionUseCase;
import by.grsu.skydiving.application.port.in.UpdateCollegiumOfCompetitionUseCase.UpdateCollegiumCommand;
import by.grsu.skydiving.application.port.out.UpdateCompetitionUseCase;
import by.grsu.skydiving.application.port.out.UpdateCompetitionUseCase.UpdateCompetitionCommand;
import by.grsu.skydiving.common.WebAdapter;
import jakarta.validation.Valid;
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
    private final AddCollegiumToCompetitionUseCase addCollegiumUseCase;
    private final UpdateCollegiumOfCompetitionUseCase updateCollegiumOfCompetitionUseCase;
    private final GetCompetitionPageUseCase pageUseCase;
    private final GetCompetitionUseCase getCompetitionUseCase;
    private final UpdateCompetitionUseCase updateCompetitionUseCase;
    private final DeleteCompetitionUseCase deleteCompetitionUseCase;
    private final CompetitionMapper competitionMapper;
    private final CollegiumMapper collegiumMapper;

    @GetMapping("/{competitionId}")
    public CompetitionResponse getCompetitionById(@PathVariable
                                                  long competitionId) {
        var competition = getCompetitionUseCase.getCompetition(competitionId);

        return competitionMapper.toResponse(competition);
    }

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
    public InitiateCompetitionResponse initiateCompetitionCreation(@Valid
                                                                   @RequestBody
                                                                   InitiateCompetitionRequest request) {
        InitiateCompetitionCommand command = competitionMapper.toCommand(request);
        Competition competition = initiateUseCase.initiateCompetition(command);

        return new InitiateCompetitionResponse(competition.getId());
    }

    @PostMapping("/{competitionId}/collegium")
    @ResponseStatus(HttpStatus.CREATED)
    public CollegiumResponse addCollegiumToCompetition(
        @PathVariable
        Long competitionId,
        @Valid
        @RequestBody
        AddCollegiumRequest request
    ) {
        AddCollegiumCommand command = collegiumMapper.toCommand(competitionId, request);
        CompetitionCollegium collegium = addCollegiumUseCase.addCollegium(command);

        return new CollegiumResponse(competitionId, collegium.id());
    }

    @PutMapping("/{competitionId}/collegium")
    @ResponseStatus(HttpStatus.CREATED)
    public CollegiumResponse updateCollegiumOfCompetition(
        @PathVariable
        Long competitionId,
        @Valid
        @RequestBody
        UpdateCollegiumRequest request
    ) {
        UpdateCollegiumCommand
            command = collegiumMapper.toCommand(competitionId, request);
        CompetitionCollegium collegium = updateCollegiumOfCompetitionUseCase.update(command);

        return new CollegiumResponse(competitionId, collegium.id());
    }

    @PutMapping("/{competitionId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CompetitionShortInfoResponse updateCompetition(@PathVariable
                                                          Long competitionId,
                                                          @Valid
                                                          @RequestBody
                                                          UpdateCompetitionRequest request) {
        UpdateCompetitionCommand command = competitionMapper.toCommand(competitionId, request);

        Competition competition = updateCompetitionUseCase.updateCompetition(command);

        return competitionMapper.toShortResponse(competition);
    }

    @DeleteMapping("/{competitionId}")
    public void deleteCompetition(@PathVariable Long competitionId) {
        deleteCompetitionUseCase.deleteCompetition(competitionId);
    }
}
