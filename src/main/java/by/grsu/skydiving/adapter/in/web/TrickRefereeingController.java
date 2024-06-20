package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.TrickAttemptMapper;
import by.grsu.skydiving.adapter.in.web.mapper.TrickRefereeingMapper;
import by.grsu.skydiving.adapter.in.web.mapper.TrickSerieResponseMapper;
import by.grsu.skydiving.adapter.in.web.request.AddTrickRefereeingRequest;
import by.grsu.skydiving.adapter.in.web.request.TrickAttemptRequest;
import by.grsu.skydiving.adapter.in.web.request.UpdateTrickAttemptsRequest;
import by.grsu.skydiving.adapter.in.web.request.UpdateTrickSerieRequest;
import by.grsu.skydiving.adapter.in.web.response.*;
import by.grsu.skydiving.application.domain.model.trick.*;
import by.grsu.skydiving.application.port.in.*;
import by.grsu.skydiving.common.WebAdapter;
import by.grsu.skydiving.common.config.security.UserDetailsServiceImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("api/v1/trick-refereeing")
@RequiredArgsConstructor
public class TrickRefereeingController {
    private final AddTrickRefereeingUseCase addTrickRefereeingUseCase;
    private final AddTrickAttemptsUseCase addTrickAttemptsUseCase;
    private final GetRefereeingsUseCase getRefereeingsUseCase;
    private final GetTrickSeriesByCompetitionIdUseCase getTrickSeriesByCompetitionIdUseCase;
    private final GetTrickSerieShortInfoUseCse getTrickSerieShortInfoUseCse;
    private final UpdateTrickSerieUseCase updateTrickSerieUseCase;
    private final GetTimeWithoutPenaltyUseCase getTimeWithoutPenaltyUseCase;
    private final UpdateTrickAttemptsUseCase updateTrickAttemptsUseCase;
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
                                                              TrickAttemptRequest request) {
        var command = attemptsMapper.toCommand(request);
        TrickAttemptsWithScore trickAttempts = addTrickAttemptsUseCase.addTrickAttempts(command);

        return attemptsMapper.toResponse(trickAttempts);
    }

    @GetMapping("/current")
    @ResponseStatus(HttpStatus.OK)
    public List<RefereeingResponse> getRefereeings() {
        List<Refereeing> refereeings = getRefereeingsUseCase.getCurrentRefereeing(getCurrentUserId());

        return serieMapper.toResponses(refereeings);
    }

    @GetMapping("/trick-series/competition/{competitionId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TrickSerieOfSkydiverResponse> getSkydiversTrick(@PathVariable long competitionId){
        List<TrickSerieOfSkydiver> trickSerieOfSkydivers = getTrickSeriesByCompetitionIdUseCase.getByCompetitionId(competitionId);

        return serieMapper.mapToResponses(trickSerieOfSkydivers);
    }

    @GetMapping("/trick-series/{trickSerieId}")
    @ResponseStatus(HttpStatus.OK)
    public TrickSerieWithCompetitionShortInfoResponse getTrickSerieShortInfo(@PathVariable Long trickSerieId){
        TrickSerieShortInfo shortInfoTrickSerie = getTrickSerieShortInfoUseCse.getTrickSerieShortInfoBeTrickSerieId(trickSerieId);

        return serieMapper.toResponse(shortInfoTrickSerie);
    }

    @PatchMapping("/trick-series")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedTrickSerieResponse updateTrickSerie(@RequestBody UpdateTrickSerieRequest request){
        TrickSerieInfoForUpdate updatedTrickSerie = updateTrickSerieUseCase.updateTrickSerie(serieMapper.toCommand(request));

        return serieMapper.toResponse(updatedTrickSerie);
    }

    @PutMapping("/trick-attempts")
    @ResponseStatus(HttpStatus.OK)
    public UpdateTrickAttemptsResponse updateTrickAttempts(@RequestBody UpdateTrickAttemptsRequest request){
        TrickAttemptsUpdate domain = attemptsMapper.toDomain(request);
        domain = updateTrickAttemptsUseCase.updateTrickAttempts(domain);

        return attemptsMapper.toResponse(domain);
    }

    @GetMapping("/trick-series/{trickSerieId}/time-without-penalty")
    @ResponseStatus(HttpStatus.OK)
    public TrickSerieTimeResponse getTimeWithoutPenalty(@PathVariable Long trickSerieId){
        TrickSerieTime trickSerieTime = getTimeWithoutPenaltyUseCase.getTrickSerieTime(trickSerieId);

        return serieMapper.toResponse(trickSerieTime);
    }

    public Long getCurrentUserId() {
        var currentUser =
            (UserDetailsServiceImpl.UserDetailsWithId) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        return currentUser.getId();
    }
}
