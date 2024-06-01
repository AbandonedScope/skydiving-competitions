package by.grsu.skydiving.adapter.in.web.mapper;

import static by.grsu.skydiving.application.port.in.ExchangeTeamMemberWithIndividualUseCase.ExchangeTeamMemberWithIndividualCommand;

import by.grsu.skydiving.adapter.in.web.request.CompetitionMemberRequest;
import by.grsu.skydiving.adapter.in.web.request.ExchangeTeamMemberWithIndividualRequest;
import by.grsu.skydiving.adapter.in.web.request.TeamRequest;
import by.grsu.skydiving.adapter.in.web.response.MembersOfCompetitionResponse;
import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import by.grsu.skydiving.application.domain.model.competition.MembersOfCompetition;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.port.in.AddTeamToCompetitionUseCase.AddTeamToCompetitionCommand;
import by.grsu.skydiving.application.port.in.UpdateTeamInCompetitionUseCase.UpdateTeamInCompetitionCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface TeamMapper {
    @Mapping(target = "competitionId", source = "competitionId")
    @Mapping(target = "team", source = "request")
    AddTeamToCompetitionCommand toAddCommand(long competitionId, TeamRequest request);

    @Mapping(target = "competitionId", source = "competitionId")
    @Mapping(target = "teamId", source = "teamId")
    @Mapping(target = "teamMemberId", source = "request.teamMemberId")
    @Mapping(target = "individualId", source = "request.individualId")
    ExchangeTeamMemberWithIndividualCommand toExchangeCommand(long competitionId, long teamId,
                                                              ExchangeTeamMemberWithIndividualRequest request);

    @Mapping(target = "competitionId", source = "competitionId")
    @Mapping(target = "team", source = "request")
    UpdateTeamInCompetitionCommand toUpdateCommand(long competitionId, long teamId, TeamRequest request);

    @Mapping(target = "members", source = "members")
    Team toDomain(TeamRequest request);

    CompetitionMember toDomain(CompetitionMemberRequest request);

    MembersOfCompetitionResponse toResponse(long competitionId, MembersOfCompetition domain);
}
