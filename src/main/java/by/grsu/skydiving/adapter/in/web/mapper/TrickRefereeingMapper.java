package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.AddTrickRefereeingRequest;
import by.grsu.skydiving.adapter.in.web.response.TrickRefereeingResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickSerieShortInfoResponse;
import by.grsu.skydiving.application.port.in.AddTrickRefereeingUseCase;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface TrickRefereeingMapper {
    AddTrickRefereeingUseCase.AddTrickRefereeingCommand toCommand(AddTrickRefereeingRequest request);

    @Mapping(target = "trickSeries", source = "domains")
    @Mapping(target = "serieNumber", source = "request.serieNumber")
    @Mapping(target = "roundNumber", source = "request.roundNumber")
    @Mapping(target = "skydiverNumber", source = "skydiverNumber")
    @Mapping(target = "skydiverId", source = "request.skydiverId")
    @Mapping(target = "competitionId", source = "request.competitionId")
    TrickRefereeingResponse toResponse(List<TrickSerieShortInfoResponse> domains, AddTrickRefereeingRequest request,
                                       Integer skydiverNumber);
}
