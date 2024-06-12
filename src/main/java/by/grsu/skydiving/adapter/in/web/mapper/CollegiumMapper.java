package by.grsu.skydiving.adapter.in.web.mapper;

import static by.grsu.skydiving.application.port.in.UpdateCollegiumOfCompetitionUseCase.UpdateCollegiumCommand;

import by.grsu.skydiving.adapter.in.web.request.AddCollegiumRequest;
import by.grsu.skydiving.adapter.in.web.request.CollegiumRefereeRequest;
import by.grsu.skydiving.adapter.in.web.request.UpdateCollegiumRequest;
import by.grsu.skydiving.adapter.in.web.response.CompetitionCollegiumResponse;
import by.grsu.skydiving.adapter.in.web.response.RefereeResponse;
import by.grsu.skydiving.application.domain.model.RefereeCollegium;
import by.grsu.skydiving.application.domain.model.competition.CollegiumReferee;
import by.grsu.skydiving.application.domain.model.competition.CompetitionCollegium;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.port.in.AddCollegiumToCompetitionUseCase.AddCollegiumCommand;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface CollegiumMapper {

    @Mapping(target = "collegium.mainCollegium.collegium", source = "request.mainCollegium")
    @Mapping(target = "collegium.collegium.collegium", source = "request.collegium")
    AddCollegiumCommand toCommand(Long competitionId, AddCollegiumRequest request);

    @Mapping(target = "collegium.mainCollegium", source = "request.mainCollegium")
    @Mapping(target = "collegium.collegium", source = "request.collegium")
    @Mapping(target = "collegium.id", source = "request.collegiumId")
    UpdateCollegiumCommand toCommand(Long competitionId, UpdateCollegiumRequest request);

    CompetitionCollegiumResponse toResponse(CompetitionCollegium collegium);

    default Set<RefereeResponse> map(RefereeCollegium domain) {
        return domain.collegium().stream()
            .map(collegiumReferee -> RefereeResponse.builder()
                .id(collegiumReferee.referee().id())
                .firstName(collegiumReferee.referee().name().firstName())
                .secondName(collegiumReferee.referee().name().secondName())
                .patronymic(collegiumReferee.referee().name().patronymic())
                .category(collegiumReferee.referee().category())
                .workPerformed(collegiumReferee.workPerformed())
                .refereeNumber(collegiumReferee.refereeNumber())
                .build()
            )
            .collect(Collectors.toSet());
    }

    default RefereeCollegium map(Set<CollegiumRefereeRequest> collegiumRequest) {
        var collegium = collegiumRequest.stream()
            .map(this::map)
            .collect(Collectors.toSet());

        return new RefereeCollegium(collegium);
    }

    default CollegiumReferee map(CollegiumRefereeRequest request) {
        return CollegiumReferee.builder()
            .referee(new Referee(request.refereeId(), null, null))
            .workPerformed(request.workPerformed())
            .refereeNumber(request.refereeNumber())
            .build();
    }
}
