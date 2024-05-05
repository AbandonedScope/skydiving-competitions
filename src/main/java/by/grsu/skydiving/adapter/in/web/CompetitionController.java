package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.CompetitionMapper;
import by.grsu.skydiving.adapter.in.web.request.InitiateCompetitionRequest;
import by.grsu.skydiving.adapter.in.web.response.InitiateCompetitionResponse;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.port.in.InitiateCompetitionUseCase;
import by.grsu.skydiving.application.port.in.InitiateCompetitionUseCase.InitiateCompetitionCommand;
import by.grsu.skydiving.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("api/v1/competitions")
@RequiredArgsConstructor
public class CompetitionController {
    private final InitiateCompetitionUseCase initiateUseCase;
    private final CompetitionMapper mapper;

    @PostMapping("/initial")
    public InitiateCompetitionResponse initiateCompetitionCreation(@RequestBody
                                                                   InitiateCompetitionRequest request) {
        InitiateCompetitionCommand command = mapper.toCommand(request);
        Competition competition = initiateUseCase.initiateCompetition(command);

        return new InitiateCompetitionResponse(competition.getId());
    }
}
