package by.grsu.skydiving.adapter.in.web;

import static by.grsu.skydiving.application.domain.model.common.FilteringFieldsNames.CATEGORY_FILTER;
import static by.grsu.skydiving.application.domain.model.common.FilteringFieldsNames.NAME_FILTER;

import by.grsu.skydiving.adapter.in.web.mapper.CollegiumMapper;
import by.grsu.skydiving.adapter.in.web.mapper.RefereeMapper;
import by.grsu.skydiving.adapter.in.web.request.AddRefereeRequest;
import by.grsu.skydiving.adapter.in.web.response.AddRefereeResponse;
import by.grsu.skydiving.adapter.in.web.response.CompetitionCollegiumResponse;
import by.grsu.skydiving.adapter.in.web.response.PageResponse;
import by.grsu.skydiving.adapter.in.web.response.RefereeShortInfoResponse;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.common.FilterQuery;
import by.grsu.skydiving.application.domain.model.common.GetPageQuery;
import by.grsu.skydiving.application.domain.model.competition.CompetitionCollegium;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.domain.model.competition.RefereeCategory;
import by.grsu.skydiving.application.port.in.AddRefereeUseCase;
import by.grsu.skydiving.application.port.in.DeleteRefereeFromCompetitionCollegiumUseCase;
import by.grsu.skydiving.application.port.in.DeleteRefereeUseCase;
import by.grsu.skydiving.application.port.in.GetCollegiumOfCompetitionUseCase;
import by.grsu.skydiving.application.port.in.GetFilteredRefereesUseCase;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("api/v1/referees")
@RequiredArgsConstructor
public class RefereesController {
    private final GetCollegiumOfCompetitionUseCase getCollegiumOfCompetitionUseCase;
    private final DeleteRefereeUseCase deleteRefereeUseCase;
    private final AddRefereeUseCase addRefereeUseCase;
    private final DeleteRefereeFromCompetitionCollegiumUseCase deleteRefereeFromCompetitionCollegiumUseCase;
    private final GetFilteredRefereesUseCase getFilteredRefereesUseCase;
    private final CollegiumMapper collegiumMapper;
    private final RefereeMapper mapper;

    @GetMapping("/{competitionId}")
    @ResponseStatus(HttpStatus.OK)
    public CompetitionCollegiumResponse getRefereeGroupsByCompetitionCollegiumId(@PathVariable Long competitionId) {
        CompetitionCollegium collegium = getCollegiumOfCompetitionUseCase
            .getByCompetitionId(competitionId);

        return collegiumMapper.toResponse(collegium);
    }

    @DeleteMapping("/{refereeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReferee(@PathVariable Long refereeId) {
        deleteRefereeUseCase.deleteRefereeByRefereeId(refereeId);
    }

    @DeleteMapping("/{refereeId}/competition/{competitionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRefereeByCompetitionCollegiumIdAndRefereeId(@PathVariable Long competitionId,
                                                                  @PathVariable Long refereeId) {
        deleteRefereeFromCompetitionCollegiumUseCase.deleteByCollegiumId(competitionId,
            refereeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddRefereeResponse addReferee(@RequestBody AddRefereeRequest refereeRequest) {
        Referee referee = mapper.toDomain(refereeRequest);
        Long refereeId = addRefereeUseCase.addReferee(referee);

        return new AddRefereeResponse(refereeId);
    }

    @GetMapping("/page")
    public PageResponse<RefereeShortInfoResponse> getRefereePage(
        @RequestParam(defaultValue = "1")
        long number,
        @RequestParam(defaultValue = "15")
        int size,
        @RequestParam(required = false)
        String name,
        @RequestParam(required = false)
        RefereeCategory category
    ) {
        Map<String, Object> filters = HashMap.newHashMap(5);
        filters.put(NAME_FILTER, name);
        filters.put(CATEGORY_FILTER, category);
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
