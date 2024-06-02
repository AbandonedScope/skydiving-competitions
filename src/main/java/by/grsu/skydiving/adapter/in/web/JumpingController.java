package by.grsu.skydiving.adapter.in.web;

import by.grsu.skydiving.adapter.in.web.mapper.JumpingMapper;
import by.grsu.skydiving.adapter.in.web.request.CreateJumpingRequest;
import by.grsu.skydiving.adapter.in.web.request.UpdateJumpingRequest;
import by.grsu.skydiving.adapter.in.web.response.CompetitionMemberJumpingResponse;
import by.grsu.skydiving.adapter.in.web.response.JumpingInfoResponse;
import by.grsu.skydiving.adapter.in.web.response.NextJumpingNumberResponse;
import by.grsu.skydiving.application.domain.model.jumping.CompetitionMemberJumping;
import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;
import by.grsu.skydiving.application.domain.model.jumping.NextJumpingNumber;
import by.grsu.skydiving.application.port.in.CreateCompetitionJumpingUseCase;
import by.grsu.skydiving.application.port.in.DeleteJumpingUseCase;
import by.grsu.skydiving.application.port.in.GetListOfJumpingForCompetitionMemberUseCase;
import by.grsu.skydiving.application.port.in.UpdateCompetitionJumpingUseCase;
import by.grsu.skydiving.application.port.out.GetNextNumberOfJumpingPort;
import by.grsu.skydiving.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("api/v1/jumping")
@RequiredArgsConstructor
public class JumpingController {
    private final CreateCompetitionJumpingUseCase createCompetitionJumpingUseCase;
    private final GetNextNumberOfJumpingPort getNextNumberOfJumpingPort;
    private final GetListOfJumpingForCompetitionMemberUseCase getListOfJumpingForCompetitionMemberUseCase;
    private final UpdateCompetitionJumpingUseCase updateCompetitionJumpingUseCase;
    private final DeleteJumpingUseCase deleteJumpingUseCase;
    private final JumpingMapper mapper;

    @PostMapping("/competition/{competitionId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void postJumpingAccuracy(
        @PathVariable
        long competitionId,
        @RequestBody
        CreateJumpingRequest createJumpingRequest
    ) {
        var createCompetitionJumpingCommand = mapper.toCreateCommand(competitionId, createJumpingRequest);

        createCompetitionJumpingUseCase.create(createCompetitionJumpingCommand);
    }

    @GetMapping("/competition/{competitionId}/skydiver/{skydiverId}")
    public NextJumpingNumberResponse getNextJumpingNumber(
        @PathVariable
        long competitionId,
        @PathVariable
        long skydiverId
    ) {
        NextJumpingNumber nextJumpingNumber =
            getNextNumberOfJumpingPort.genNextNumberOfJumping(competitionId, skydiverId);
        return new NextJumpingNumberResponse(
            nextJumpingNumber.nextJumpingNumber(),
            nextJumpingNumber.isLimitReached()
        );
    }

    @GetMapping("/competition/{competitionId}/skydiver/{skydiverId}/list")
    public CompetitionMemberJumpingResponse getJumping(
        @PathVariable
        long competitionId,
        @PathVariable
        long skydiverId
    ) {
        CompetitionMemberJumping competitionMemberJumping =
            getListOfJumpingForCompetitionMemberUseCase.getListOfJumping(competitionId, skydiverId);

        return mapper.toResponse(competitionId, skydiverId, competitionMemberJumping);
    }

    @PutMapping("/competition/{competitionId}/skydiver/{skydiverId}")
    public JumpingInfoResponse updateJumping(
        @PathVariable
        long competitionId,
        @PathVariable
        long skydiverId,
        @RequestBody
        UpdateJumpingRequest request
    ) {
        var updateCommand = mapper.toUpdateCommand(competitionId, skydiverId, request);

        JumpingInfo jumpingInfo = updateCompetitionJumpingUseCase.update(updateCommand);
        return mapper.toResponse(jumpingInfo);
    }

    @DeleteMapping("/{jumpingId}/competition/{competitionId}")
    public void deleteJumping(
        @PathVariable
        long jumpingId,
        @PathVariable
        long competitionId
    ) {
        deleteJumpingUseCase.delete(competitionId, jumpingId);
    }
}
