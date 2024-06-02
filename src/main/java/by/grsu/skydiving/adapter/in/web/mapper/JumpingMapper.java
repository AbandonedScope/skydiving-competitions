package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.CreateJumpingRequest;
import by.grsu.skydiving.adapter.in.web.response.CompetitionMemberJumpingResponse;
import by.grsu.skydiving.application.domain.model.jumping.CompetitionMemberJumping;
import by.grsu.skydiving.application.port.in.CreateCompetitionJumpingUseCase.CreateCompetitionJumpingCommand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface JumpingMapper {
    CreateCompetitionJumpingCommand toCommand(long competitionId, CreateJumpingRequest request);

    CompetitionMemberJumpingResponse toResponse(long competitionId, long skydiverId, CompetitionMemberJumping domain);
}
