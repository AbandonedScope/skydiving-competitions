package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.RefereeMapper;
import by.grsu.skydiving.adapter.in.web.response.RefereeGroupsResponse;
import by.grsu.skydiving.application.domain.model.competition.RefereeGroups;
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
    private final RefereeMapper mapper;

    @PostMapping("/{competitionStageId}")
    @ResponseStatus(HttpStatus.OK)
    public RefereeGroupsResponse getRefereeGroupsByCompetitionStageId(@PathVariable Long competitionStageId) {
        RefereeGroups referees = getRefereesGroupsUseCase.findRefereesByCompetitionStageId(competitionStageId);

        return mapper.toResponse(referees);
    }
}
