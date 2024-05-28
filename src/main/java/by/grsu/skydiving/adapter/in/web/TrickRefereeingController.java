package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.TrickRefereeingMapper;
import by.grsu.skydiving.adapter.in.web.mapper.TrickSerieResponseMapper;
import by.grsu.skydiving.adapter.in.web.request.AddTrickRefereeingRequest;
import by.grsu.skydiving.adapter.in.web.response.TrickRefereeingResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickSerieShortInfoResponse;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickRefereeing;
import by.grsu.skydiving.application.port.in.AddTrickRefereeingUseCase;
import by.grsu.skydiving.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@WebAdapter
@RestController
@RequestMapping("api/v1/trick-refereeing")
@RequiredArgsConstructor
public class TrickRefereeingController {
    private final AddTrickRefereeingUseCase addTrickRefereeingUseCase;
    private final TrickRefereeingMapper refereeingMapper;
    private final TrickSerieResponseMapper serieMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrickRefereeingResponse addTrickRefereeing(@RequestBody
                                                       AddTrickRefereeingRequest request) {
        var command = refereeingMapper.toCommand(request);
        TrickRefereeing trickRefereeing = addTrickRefereeingUseCase.addTrickRefereeing(command);
        List<TrickSerieShortInfoResponse> trickSeriesResponses = new ArrayList<>();
        trickRefereeing.trickSeries().forEach(t -> trickSeriesResponses.add(serieMapper.toResponse(t, request)));

        return refereeingMapper.toResponse(trickSeriesResponses, request, trickRefereeing.skydiverNumber());
    }
}
