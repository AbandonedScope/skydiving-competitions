package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.RefereeMapper;
import by.grsu.skydiving.adapter.in.web.request.AddRefereeRequest;
import by.grsu.skydiving.adapter.in.web.response.AddRefereeResponse;
import by.grsu.skydiving.adapter.in.web.response.RefereeGroupsResponse;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.domain.model.competition.RefereeGroups;
import by.grsu.skydiving.application.port.in.AddRefereeUseCase;
import by.grsu.skydiving.application.port.in.DeleteRefereeUseCase;
import by.grsu.skydiving.application.port.in.GetRefereesGroupsByCompetitionStageIdUseCase;
import by.grsu.skydiving.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("api/v1/referees")
@RequiredArgsConstructor
public class RefereesController {
    private final GetRefereesGroupsByCompetitionStageIdUseCase getRefereesGroupsUseCase;
    private final DeleteRefereeUseCase deleteRefereeUseCase;
    private final AddRefereeUseCase addRefereeUseCase;
    private final RefereeMapper mapper;

    @PostMapping("/competitionStage/{competitionStageId}")
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

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public AddRefereeResponse addReferee(@RequestBody AddRefereeRequest refereeRequest) {
        Referee referee = mapper.toDomain(refereeRequest);
        Long refereeId = addRefereeUseCase.addReferee(referee);

        return new AddRefereeResponse(refereeId);
    }
}
