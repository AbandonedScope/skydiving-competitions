package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.AddTrickRefereeingRequest;
import by.grsu.skydiving.adapter.in.web.response.TrickSerieShortInfoResponse;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickSerie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface TrickSerieResponseMapper {
    @Mapping(target = "skydiverId", source = "request.skydiverId")
    @Mapping(target = "competitionId", source = "request.competitionId")
    @Mapping(target = "id", source = "domain.id")
    @Mapping(target = "refereeId", source = "domain.refereeId")
    @Mapping(target = "refereeNumber", source = "domain.refereeNumber")
    @Mapping(target = "serieNumber", source = "domain.serieNumber")
    @Mapping(target = "roundNumber", source = "domain.roundNumber")
    @Mapping(target = "score", source = "domain.score")
    @Mapping(target = "timeWithoutPenalty", source = "domain.timeWithoutPenalty")
    @Mapping(target = "totalPenalty", source = "domain.totalPenalty")
    @Mapping(target = "isTimeSubmitted", source = "domain.isTimeSubmitted")
    TrickSerieShortInfoResponse toResponse(TrickSerie domain, AddTrickRefereeingRequest request);
}
