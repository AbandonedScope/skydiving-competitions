package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.TeamMapper;
import by.grsu.skydiving.adapter.in.web.request.CompetitionMemberRequest;
import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import by.grsu.skydiving.application.port.in.AddIndividualToCompetitionUseCase;
import by.grsu.skydiving.application.port.in.DeleteIndividualFromCompetitionUseCase;
import by.grsu.skydiving.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("api/v1/individuals")
@RequiredArgsConstructor
public class IndividualsController {
    private final AddIndividualToCompetitionUseCase addIndividualToCompetitionUseCase;
    private final DeleteIndividualFromCompetitionUseCase deleteIndividualFromCompetitionUseCase;
    private final TeamMapper teamMapper;

    @PostMapping("/competition/{competitionId}")
    public void addIndividualToCompetition(@PathVariable
                                           long competitionId,
                                           @RequestBody
                                           CompetitionMemberRequest request) {
        CompetitionMember individual = teamMapper.toDomain(request);

        addIndividualToCompetitionUseCase.addIndividualToCompetition(competitionId, individual);
    }

    @DeleteMapping("/{individualId}/competition/{competitionId}")
    public void deleteIndividualFromCompetition(@PathVariable
                                                long competitionId,
                                                @PathVariable
                                                long individualId) {
        deleteIndividualFromCompetitionUseCase.delete(competitionId, individualId);
    }
}
