package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.JumpingMapper;
import by.grsu.skydiving.adapter.in.web.request.CreateJumpingRequest;
import by.grsu.skydiving.adapter.in.web.response.NextJumpingNumberResponse;
import by.grsu.skydiving.application.port.in.CreateCompetitionJumpingUseCase;
import by.grsu.skydiving.application.port.out.GetNextNumberOfJumpingPort;
import by.grsu.skydiving.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("api/v1/jumping")
@RequiredArgsConstructor
public class JumpingController {
    private final GetNextNumberOfJumpingPort getNextNumberOfJumpingPort;
    private final CreateCompetitionJumpingUseCase createCompetitionJumpingUseCase;
    private final JumpingMapper mapper;

    @GetMapping("/competition/{competitionId}/skydiver/{skydiverId}")
    public NextJumpingNumberResponse getNextJumpingNumber(
        @PathVariable
        long competitionId,
        @PathVariable
        long skydiverId
    ) {
        int nextNumberOfJumping = getNextNumberOfJumpingPort.genNextNumberOfJumping(competitionId, skydiverId);
        return new NextJumpingNumberResponse(nextNumberOfJumping);
    }

    @PostMapping("/competition/{competitionId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void postJumpingAccuracy(
        @PathVariable
        long competitionId,
        @RequestBody
        CreateJumpingRequest createJumpingRequest
    ) {
        var createCompetitionJumpingCommand = mapper.toCommand(competitionId, createJumpingRequest);

        createCompetitionJumpingUseCase.create(createCompetitionJumpingCommand);
    }
}
