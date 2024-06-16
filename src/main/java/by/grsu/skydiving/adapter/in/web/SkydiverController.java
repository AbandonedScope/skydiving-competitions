package by.grsu.skydiving.adapter.in.web;

import static by.grsu.skydiving.application.domain.model.common.FilteringFieldsNames.GENDER_FILTER;
import static by.grsu.skydiving.application.domain.model.common.FilteringFieldsNames.IS_INTERNAL_FILTER;
import static by.grsu.skydiving.application.domain.model.common.FilteringFieldsNames.NAME_FILTER;
import static by.grsu.skydiving.application.domain.model.common.FilteringFieldsNames.SPORT_RANK_FILTER;

import by.grsu.skydiving.adapter.in.web.mapper.SkydiverMapper;
import by.grsu.skydiving.adapter.in.web.request.AddExternalSkydiverRequest;
import by.grsu.skydiving.adapter.in.web.request.AddSkydiverRequest;
import by.grsu.skydiving.adapter.in.web.request.UpdateExternalSkydiverRequest;
import by.grsu.skydiving.adapter.in.web.request.UpdateSkydiverRequest;
import by.grsu.skydiving.adapter.in.web.response.ExternalSkydiverResponse;
import by.grsu.skydiving.adapter.in.web.response.PageResponse;
import by.grsu.skydiving.adapter.in.web.response.SkydiverResponse;
import by.grsu.skydiving.adapter.in.web.response.SkydiverShortInfoResponse;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.common.FilterQuery;
import by.grsu.skydiving.application.domain.model.common.GetPageQuery;
import by.grsu.skydiving.application.domain.model.skydiver.Gender;
import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import by.grsu.skydiving.application.domain.model.skydiver.SportRank;
import by.grsu.skydiving.application.port.in.AddExternalSkydiverUseCase;
import by.grsu.skydiving.application.port.in.AddSkydiverUseCase;
import by.grsu.skydiving.application.port.in.GetSkydiverUseCase;
import by.grsu.skydiving.application.port.in.GetSkydiversPageUseCase;
import by.grsu.skydiving.application.port.in.SoftDeleteSkydiverUseCase;
import by.grsu.skydiving.application.port.in.UpdateExternalSkydiverUseCase;
import by.grsu.skydiving.application.port.in.UpdateSkydiverUseCase;
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
@RequestMapping("api/v1/skydivers")
@RequiredArgsConstructor
public class SkydiverController {
    private final AddSkydiverUseCase addUseCase;
    private final UpdateSkydiverUseCase updateUseCase;
    private final AddExternalSkydiverUseCase addExternalUseCase;
    private final UpdateExternalSkydiverUseCase updateExternalUseCase;
    private final GetSkydiverUseCase getSkydiverUseCase;
    private final GetSkydiversPageUseCase pageUseCase;
    private final SoftDeleteSkydiverUseCase softDeleteUseCase;
    private final SkydiverMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkydiverShortInfoResponse addSkydiver(@RequestBody
                                                 AddSkydiverRequest request) {
        Skydiver skydiver = mapper.toDomain(request);
        skydiver = addUseCase.addSkydiver(skydiver);

        return mapper.toShortResponse(skydiver);
    }

    @PostMapping("/external")
    @ResponseStatus(HttpStatus.CREATED)
    public ExternalSkydiverResponse addExternalSkydiver(@RequestBody
                                                        AddExternalSkydiverRequest request) {
        Skydiver skydiver = mapper.toDomain(request);
        skydiver = addExternalUseCase.addExternalSkydiver(skydiver);

        return mapper.toExternalResponse(skydiver);
    }

    @GetMapping("/{skydiverId}")
    public SkydiverResponse getSkydiver(@PathVariable
                                        Long skydiverId) {
        Skydiver skydiver = getSkydiverUseCase.getById(skydiverId);

        return mapper.toResponse(skydiver);
    }

    @GetMapping("/page")
    public PageResponse<SkydiverShortInfoResponse> getSkydiverPage(
        @RequestParam(defaultValue = "1")
        long number,
        @RequestParam(defaultValue = "15")
        int size,
        @RequestParam(required = false)
        String name,
        @RequestParam(required = false)
        Gender gender,
        @RequestParam(required = false)
        SportRank sportRank,
        @RequestParam(required = false)
        Boolean isInternal
    ) {
        Map<String, Object> filters = HashMap.newHashMap(7);
        filters.put(NAME_FILTER, name);
        filters.put(GENDER_FILTER, gender);
        filters.put(SPORT_RANK_FILTER, sportRank);
        filters.put(IS_INTERNAL_FILTER, isInternal);
        filters.values().removeIf(Objects::isNull);

        FilterQuery filterQuery = new FilterQuery(filters);
        GetPageQuery pageQuery = GetPageQuery.builder()
            .pageNumber(number)
            .pageSize(size)
            .filterQuery(filterQuery)
            .build();

        DomainPage<SkydiverShortInfo> page = pageUseCase.getPage(pageQuery);

        return mapper.toResponse(page);
    }

    @PutMapping("/{skydiverId}")
    public SkydiverResponse updateSkydiver(@PathVariable
                                           Long skydiverId,
                                           @RequestBody
                                           UpdateSkydiverRequest request) {
        Skydiver skydiver = mapper.toDomain(skydiverId, request);

        skydiver = updateUseCase.updateInternal(skydiverId, skydiver);

        return mapper.toResponse(skydiver);
    }

    @PutMapping("/external/{skydiverId}")
    public SkydiverShortInfoResponse updateSkydiver(@PathVariable
                                                    Long skydiverId,
                                                    @RequestBody
                                                    UpdateExternalSkydiverRequest request) {
        Skydiver skydiver = mapper.toDomain(skydiverId, request);

        skydiver = updateExternalUseCase.updateExternal(skydiverId, skydiver);

        return mapper.toShortResponse(skydiver);
    }

    @DeleteMapping("/{skydiverId}")
    public void softDelete(@PathVariable
                           long skydiverId) {
        softDeleteUseCase.softDelete(skydiverId);
    }
}
