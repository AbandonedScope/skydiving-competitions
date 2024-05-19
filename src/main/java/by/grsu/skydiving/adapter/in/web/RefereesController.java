package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.RefereeMapper;
import by.grsu.skydiving.adapter.in.web.request.AddRefereeRequest;
import by.grsu.skydiving.adapter.in.web.response.AddRefereeResponse;
import by.grsu.skydiving.adapter.in.web.response.PageResponse;
import by.grsu.skydiving.adapter.in.web.response.PagedRefereeResponse;
import by.grsu.skydiving.adapter.in.web.response.RefereeGroupsResponse;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.common.FilterQuery;
import by.grsu.skydiving.application.domain.model.common.GetPageQuery;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.domain.model.competition.RefereeCategory;
import by.grsu.skydiving.application.domain.model.competition.RefereeGroups;
import by.grsu.skydiving.application.port.in.*;
import by.grsu.skydiving.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebAdapter
@RestController
@RequestMapping("api/v1/referees")
@RequiredArgsConstructor
public class RefereesController {
    private final GetRefereesGroupsByCompetitionStageIdUseCase getRefereesGroupsUseCase;
    private final DeleteRefereeUseCase deleteRefereeUseCase;
    private final AddRefereeUseCase addRefereeUseCase;
    private final DeleteRefereeFromCompetitionStageUseCase deleteRefereeFromCompetitionStageUseCase;
    private final GetFilteredRefereesUseCase getFilteredRefereesUseCase;
    private final RefereeMapper mapper;

    @GetMapping("/competitionStage/{competitionStageId}")
    @ResponseStatus(HttpStatus.OK)
    public RefereeGroupsResponse getRefereeGroupsByCompetitionStageId(@PathVariable Long competitionStageId) {
        RefereeGroups referees = getRefereesGroupsUseCase.findRefereesByCompetitionStageId(competitionStageId);

        return mapper.toResponse(referees);
    }

    @DeleteMapping("/{refereeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReferee(@PathVariable Long refereeId) {
        deleteRefereeUseCase.deleteRefereeByRefereeId(refereeId);
    }

    @DeleteMapping("/{refereeId}/competitionStage/{competitionStageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRefereeByCompetitionStageIdAndRefereeId(@PathVariable Long competitionStageId, @PathVariable Long refereeId) {
        deleteRefereeFromCompetitionStageUseCase.deleteRefereeFromCompetitionByCompetitionStageId(competitionStageId, refereeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddRefereeResponse addReferee(@RequestBody AddRefereeRequest refereeRequest) {
        Referee referee = mapper.toDomain(refereeRequest);
        Long refereeId = addRefereeUseCase.addReferee(referee);

        return new AddRefereeResponse(refereeId);
    }

    @GetMapping("/page")
    public PageResponse<PagedRefereeResponse> getRefereePage(
            @RequestParam
            long number,
            @RequestParam
            int size,
            @RequestParam(required = false)
            String name,
            @RequestParam(required = false)
            RefereeCategory category
    ) {
        Map<String, Object> filters = HashMap.newHashMap(5);
        filters.put("name", name);
        filters.put("category", category);
        filters.values().removeIf(Objects::isNull);

        FilterQuery filterQuery = new FilterQuery(filters);
        GetPageQuery pageQuery = GetPageQuery.builder()
                .pageNumber(number)
                .pageSize(size)
                .filterQuery(filterQuery)
                .build();

        DomainPage<Referee> page = getFilteredRefereesUseCase.getPage(pageQuery);

        return mapper.toResponse(page);
    }
}
