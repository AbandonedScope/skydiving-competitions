package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.PenaltyMetricsMapper;
import by.grsu.skydiving.adapter.in.web.mapper.TrickAttemptMapper;
import by.grsu.skydiving.adapter.in.web.mapper.TrickRefereeingMapper;
import by.grsu.skydiving.adapter.in.web.mapper.TrickSerieResponseMapper;
import by.grsu.skydiving.adapter.in.web.request.AddTrickRefereeingRequest;
import by.grsu.skydiving.adapter.in.web.request.TrickAttemptRequest;
import by.grsu.skydiving.adapter.in.web.response.RefereeingResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickAttemptsFullInfoResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickRefereeingResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickSerieShortInfoResponse;
import by.grsu.skydiving.application.domain.model.trickRefereeing.PenaltyValues;
import by.grsu.skydiving.application.domain.model.trickRefereeing.Refereeing;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickAttemptsWithScore;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickRefereeing;
import by.grsu.skydiving.application.port.in.AddTrickAttemptsUseCase;
import by.grsu.skydiving.application.port.in.AddTrickRefereeingUseCase;
import by.grsu.skydiving.application.port.in.GetRefereeingsUseCase;
import by.grsu.skydiving.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@WebAdapter
@RestController
@RequestMapping("api/v1/trick-refereeing")
@RequiredArgsConstructor
public class TrickRefereeingController {
    private final AddTrickRefereeingUseCase addTrickRefereeingUseCase;
    private final AddTrickAttemptsUseCase addTrickAttemptsUseCase;
    private final GetRefereeingsUseCase getRefereeingsUseCase;
    private final TrickRefereeingMapper refereeingMapper;
    private final TrickSerieResponseMapper serieMapper;
    private final TrickAttemptMapper attemptsMapper;

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

    @PostMapping("/attempts")
    @ResponseStatus(HttpStatus.CREATED)
    public TrickAttemptsFullInfoResponse addTrickAttempts(@RequestBody
                                                          TrickAttemptRequest request){
        var command = attemptsMapper.toCommand(request);
        TrickAttemptsWithScore trickAttempts = addTrickAttemptsUseCase.addTrickAttempts(command);

        return attemptsMapper.toResponse(trickAttempts);
    }

    @GetMapping("/current")
    @ResponseStatus(HttpStatus.OK)
    public List<RefereeingResponse> getRefereeings(){
        Long refereeId = Long.valueOf(getAuthentication().getName());
        List<Refereeing> refereeings = getRefereeingsUseCase.getCurrentRefereeing(refereeId);

        return serieMapper.toResponses(refereeings);
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
