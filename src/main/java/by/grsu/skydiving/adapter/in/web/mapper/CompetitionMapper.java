package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.InitiateCompetitionRequest;
import by.grsu.skydiving.adapter.in.web.request.UpdateCompetitionRequest;
import by.grsu.skydiving.adapter.in.web.response.CompetitionResponse;
import by.grsu.skydiving.adapter.in.web.response.CompetitionShortInfoResponse;
import by.grsu.skydiving.adapter.in.web.response.PageResponse;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionShortInfo;
import by.grsu.skydiving.application.port.in.InitiateCompetitionUseCase.InitiateCompetitionCommand;
import by.grsu.skydiving.application.port.out.UpdateCompetitionUseCase.UpdateCompetitionCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN,
    uses = CollegiumMapper.class
)
public interface CompetitionMapper {

    @Mapping(target = "placeOfCompetition.address", source = "place")
    InitiateCompetitionCommand toCommand(InitiateCompetitionRequest request);

    @Mapping(target = "id", source = "competitionId")
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "beginDate", source = "request.beginDate")
    @Mapping(target = "endDate", source = "request.endDate")
    @Mapping(target = "place", source = "request.place")
    UpdateCompetitionCommand toCommand(Long competitionId, UpdateCompetitionRequest request);

    @Mapping(target = "place", source = "place.address")
    CompetitionResponse toResponse(Competition competition);

    @Mapping(target = "place", source = "place.address")
    CompetitionShortInfoResponse toShortResponse(CompetitionShortInfo domain);

    @Mapping(target = "place", source = "place.address")
    CompetitionShortInfoResponse toShortResponse(Competition domain);

    PageResponse<CompetitionShortInfoResponse> toShortResponse(DomainPage<CompetitionShortInfo> domain);
}
