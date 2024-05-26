package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.TeamMapper;
import by.grsu.skydiving.adapter.in.web.response.MembersOfCompetitionResponse;
import by.grsu.skydiving.application.port.in.GetMembersOfCompetitionUseCase;
import by.grsu.skydiving.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("api/v1/teams")
@RequiredArgsConstructor
public class TeamController {
    private final GetMembersOfCompetitionUseCase getMembersOfCompetitionUseCase;
    private final TeamMapper mapper;

    @GetMapping("/{competitionId}/members")
    public MembersOfCompetitionResponse getMembersOfCompetition(@PathVariable long competitionId) {
        var members = getMembersOfCompetitionUseCase.getMembersOfCompetition(competitionId);

        return mapper.toResponse(competitionId, members);
    }
}
