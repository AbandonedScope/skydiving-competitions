package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.PenaltyMetricsRequest;
import by.grsu.skydiving.adapter.in.web.request.TrickAttemptRequest;
import by.grsu.skydiving.adapter.in.web.response.TrickAttemptResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickAttemptsFullInfoResponse;
import by.grsu.skydiving.application.domain.model.trickRefereeing.PenaltyValues;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickAttempt;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickAttemptsWithScore;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickType;
import by.grsu.skydiving.application.port.in.AddTrickAttemptsUseCase;
import java.util.HashMap;
import java.util.Map;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN
)

public interface TrickAttemptMapper {
    PenaltyMetricsMapper metricsMapper = Mappers.getMapper(PenaltyMetricsMapper.class);

    @Mapping(source = "request.trickSerieId", target = "trickSerieId")
    @Mapping(target = "trickAttempts",
        expression = "java(this.toValuesMap(request.trickAttempts()))")
    AddTrickAttemptsUseCase.AddTrickAttemptToTrickSerieCommand toCommand(TrickAttemptRequest request);

    @Mapping(source = "attempt.id", target = "id")
    @Mapping(source = "attempt.trickSerieId", target = "trickSerieId")
    @Mapping(source = "attempt.trickType", target = "type")
    @Mapping(target = "attempt.penalties", expression = "java(metricsMapper.toResponses(attempt.penalties))")
    TrickAttemptResponse toResponse(TrickAttempt attempt);

    @Mapping(source = "totalScore", target = "totalScore")
    @Mapping(target = "trickAttempts", expression = "java(this.toMap(trickAttemptsWithScore.trickAttempts()))")
    TrickAttemptsFullInfoResponse toResponse(TrickAttemptsWithScore trickAttemptsWithScore);

    default Map<TrickType, TrickAttemptResponse> toMap(Map<TrickType, TrickAttempt> trickAttempts) {
        Map<TrickType, TrickAttemptResponse> responseMap = new HashMap<>(10);
        trickAttempts.forEach((key, value) -> responseMap.put(key, toResponse(value)));

        return responseMap;
    }

    default Map<TrickType, PenaltyValues> toValuesMap(Map<TrickType, PenaltyMetricsRequest> trickAttempts) {
        Map<TrickType, PenaltyValues> valuesMap = new HashMap<>(10);
        trickAttempts.forEach((key, value) -> valuesMap.put(key, metricsMapper.toDomain(value)));

        return valuesMap;
    }
}
