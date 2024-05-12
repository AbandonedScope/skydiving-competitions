package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.SkydiverMapper;
import by.grsu.skydiving.adapter.in.web.request.SkydiverRequest;
import by.grsu.skydiving.adapter.in.web.response.PageResponse;
import by.grsu.skydiving.adapter.in.web.response.SkydiverResponse;
import by.grsu.skydiving.adapter.in.web.response.SkydiverShortInfoResponse;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.common.FilterQuery;
import by.grsu.skydiving.application.domain.model.common.GetPageQuery;
import by.grsu.skydiving.application.domain.model.skydiver.Gender;
import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import by.grsu.skydiving.application.domain.model.skydiver.SportDegree;
import by.grsu.skydiving.application.port.in.AddSkydiverUseCase;
import by.grsu.skydiving.application.port.in.GetSkydiversPageUseCase;
import by.grsu.skydiving.application.port.in.SoftDeleteSkydiverUseCase;
import by.grsu.skydiving.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("api/v1/skydivers")
@RequiredArgsConstructor
public class SkydiverController {
    private final AddSkydiverUseCase addUseCase;
    private final GetSkydiversPageUseCase pageUseCase;
    private final SoftDeleteSkydiverUseCase softDeleteUseCase;
    private final SkydiverMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkydiverResponse addSkydiver(@RequestBody
                                        SkydiverRequest request) {
        Skydiver skydiver = mapper.toDomain(request);
        skydiver = addUseCase.addSkydiver(skydiver);

        return mapper.toResponse(skydiver);
    }

    @GetMapping("/page")
    public PageResponse<SkydiverShortInfoResponse> getSkydiverPage(
            @RequestParam
            long number,
            @RequestParam
            int size,
            @RequestParam(required = false)
            String name,
            @RequestParam(required = false)
            Gender gender,
            @RequestParam(required = false)
            SportDegree sportDegree,
            @RequestParam(required = false)
            Boolean isInternal
    ) {
        FilterQuery filterQuery = FilterQuery.builder()
                .gender(gender)
                .sportDegree(sportDegree)
                .name(name)
                .isInternal(isInternal)
                .build();
        GetPageQuery pageQuery = GetPageQuery.builder()
                .pageNumber(number)
                .pageSize(size)
                .filterQuery(filterQuery)
                .build();

        DomainPage<SkydiverShortInfo> page = pageUseCase.getPage(pageQuery);

        return mapper.toResponse(page);
    }

    @DeleteMapping("/{skydiverId}")
    public void softDelete(@PathVariable
                           long skydiverId) {
        softDeleteUseCase.softDelete(skydiverId);
    }
}
