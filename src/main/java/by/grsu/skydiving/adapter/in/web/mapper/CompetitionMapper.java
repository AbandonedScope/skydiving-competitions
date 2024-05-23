package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.AddStageRequest;
import by.grsu.skydiving.adapter.in.web.request.CollegiumRefereeRequest;
import by.grsu.skydiving.adapter.in.web.request.InitiateCompetitionRequest;
import by.grsu.skydiving.adapter.in.web.request.UpdateCompetitionRequest;
import by.grsu.skydiving.adapter.in.web.response.CompetitionShortInfoResponse;
import by.grsu.skydiving.adapter.in.web.response.PageResponse;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.competition.CollegiumReferee;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionShortInfo;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.port.in.AddStageToCompetitionUseCase.AddStageCommand;
import by.grsu.skydiving.application.port.in.InitiateCompetitionUseCase.InitiateCompetitionCommand;
import by.grsu.skydiving.application.port.out.UpdateCompetitionUseCase.UpdateCompetitionCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface CompetitionMapper {

    @Mapping(target = "placeOfCompetition.address", source = "place")
    InitiateCompetitionCommand toCommand(InitiateCompetitionRequest request);

    @Mapping(target = "stage.number", source = "request.stageNumber")
    @Mapping(target = "stage.mainCollegium.collegium", source = "request.mainCollegium")
    @Mapping(target = "stage.collegium.collegium", source = "request.collegium")
    AddStageCommand toCommand(Long competitionId, AddStageRequest request);

    @Mapping(target = "id", source = "competitionId")
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "beginDate", source = "request.beginDate")
    @Mapping(target = "endDate", source = "request.endDate")
    @Mapping(target = "place", source = "request.place")
    @Mapping(target = "numberOfStages", source = "request.numberOfStages")
    UpdateCompetitionCommand toCommand(Long competitionId, UpdateCompetitionRequest request);

    @Mapping(target = "place", source = "place.address")
    CompetitionShortInfoResponse toShortResponse(CompetitionShortInfo domain);

    @Mapping(target = "place", source = "place.address")
    CompetitionShortInfoResponse toShortResponse(Competition domain);

    PageResponse<CompetitionShortInfoResponse> toShortResponse(DomainPage<CompetitionShortInfo> domain);

    default CollegiumReferee map(CollegiumRefereeRequest request) {
        return CollegiumReferee.builder()
                .referee(new Referee(request.refereeId(), null, null))
                .workPerformed(request.workPerformed())
                .build();
    }
}
