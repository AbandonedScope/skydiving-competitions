package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.TrickAttemptMapper;
import by.grsu.skydiving.adapter.in.web.mapper.TrickRefereeingMapper;
import by.grsu.skydiving.adapter.in.web.mapper.TrickSerieResponseMapper;
import by.grsu.skydiving.adapter.in.web.request.AddTrickRefereeingRequest;
import by.grsu.skydiving.adapter.in.web.request.TrickAttemptRequest;
import by.grsu.skydiving.adapter.in.web.request.UpdateTrickSerieRequest;
import by.grsu.skydiving.adapter.in.web.response.RefereeingResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickAttemptsFullInfoResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickRefereeingResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickSerieOfSkydiverResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickSerieShortInfoResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickSerieTimeResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickSerieWithCompetitionShortInfoResponse;
import by.grsu.skydiving.adapter.in.web.response.UpdatedTrickSerieResponse;
import by.grsu.skydiving.application.domain.model.trick.Refereeing;
import by.grsu.skydiving.application.domain.model.trick.TrickAttemptsWithScore;
import by.grsu.skydiving.application.domain.model.trick.TrickRefereeing;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieInfoForUpdate;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieOfSkydiver;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieShortInfo;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieTime;
import by.grsu.skydiving.application.port.in.AddTrickAttemptsUseCase;
import by.grsu.skydiving.application.port.in.AddTrickRefereeingUseCase;
import by.grsu.skydiving.application.port.in.GetRefereeingsUseCase;
import by.grsu.skydiving.application.port.in.GetTimeWithoutPenaltyUseCase;
import by.grsu.skydiving.application.port.in.GetTrickSerieShortInfoUseCse;
import by.grsu.skydiving.application.port.in.GetTrickSeriesByCompetitionIdUseCase;
import by.grsu.skydiving.application.port.in.UpdateTrickSerieUseCase;
import by.grsu.skydiving.common.WebAdapter;
import by.grsu.skydiving.common.config.security.UserDetailsServiceImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
