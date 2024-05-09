package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.SkydiverMapper;
import by.grsu.skydiving.adapter.in.web.request.SkydiverRequest;
import by.grsu.skydiving.adapter.in.web.response.PageResponse;
import by.grsu.skydiving.adapter.in.web.response.SkydiverResponse;
import by.grsu.skydiving.adapter.in.web.response.SkydiverShortInfoResponse;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.common.GetPageQuery;
import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import by.grsu.skydiving.application.port.in.AddSkydiverUseCase;
import by.grsu.skydiving.application.port.in.GetSkydiversPageUseCase;
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
    public PageResponse<SkydiverShortInfoResponse> getSkydiverPage(@RequestParam
                                                                   long number,
                                                                   @RequestParam
                                                                   int size) {
        GetPageQuery pageQuery = new GetPageQuery(number, size);
        DomainPage<SkydiverShortInfo> page = pageUseCase.getPage(pageQuery);

        return mapper.toResponse(page);
    }
}
