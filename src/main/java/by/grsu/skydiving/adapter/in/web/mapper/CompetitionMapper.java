package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.AddStageRequest;
import by.grsu.skydiving.adapter.in.web.request.CollegiumRefereeRequest;
import by.grsu.skydiving.adapter.in.web.request.InitiateCompetitionRequest;
import by.grsu.skydiving.application.domain.model.competition.CollegiumReferee;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.port.in.AddStageToCompetitionUseCase.AddStageCommand;
import by.grsu.skydiving.application.port.in.InitiateCompetitionUseCase.InitiateCompetitionCommand;
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

    default CollegiumReferee map(CollegiumRefereeRequest request) {
        return CollegiumReferee.builder()
                .referee(new Referee(request.refereeId(), null, null))
                .workPerformed(request.workPerformed())
                .build();
    }
}
