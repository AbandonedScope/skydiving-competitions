package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.CreateJumpingRequest;
import by.grsu.skydiving.adapter.in.web.request.UpdateJumpingRequest;
import by.grsu.skydiving.adapter.in.web.response.CompetitionMemberJumpingResponse;
import by.grsu.skydiving.adapter.in.web.response.JumpingInfoResponse;
import by.grsu.skydiving.application.domain.model.jumping.CompetitionMemberJumping;
import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;
import by.grsu.skydiving.application.port.in.CreateCompetitionJumpingUseCase.CreateCompetitionJumpingCommand;
import by.grsu.skydiving.application.port.in.UpdateCompetitionJumpingUseCase.UpdateCompetitionJumpingCommand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface JumpingMapper {
    CreateCompetitionJumpingCommand toCreateCommand(long competitionId, CreateJumpingRequest request);

    UpdateCompetitionJumpingCommand toUpdateCommand(long competitionId, long skydiverId, UpdateJumpingRequest request);

    CompetitionMemberJumpingResponse toResponse(long competitionId, long skydiverId, CompetitionMemberJumping domain);

    JumpingInfoResponse toResponse(JumpingInfo domain);
}
