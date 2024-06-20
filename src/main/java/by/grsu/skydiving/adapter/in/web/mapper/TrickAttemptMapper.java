package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.PenaltyMetricsRequest;
import by.grsu.skydiving.adapter.in.web.request.TrickAttemptRequest;
import by.grsu.skydiving.adapter.in.web.request.UpdateTrickAttemptRequest;
import by.grsu.skydiving.adapter.in.web.request.UpdateTrickAttemptsRequest;
import by.grsu.skydiving.adapter.in.web.response.TrickAttemptResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickAttemptsFullInfoResponse;
import by.grsu.skydiving.adapter.in.web.response.UpdateTrickAttemptResponse;
import by.grsu.skydiving.adapter.in.web.response.UpdateTrickAttemptsResponse;
import by.grsu.skydiving.application.domain.model.trick.*;
import by.grsu.skydiving.application.port.in.AddTrickAttemptsUseCase;
import java.util.EnumMap;
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
    @Mapping(source = "request.penaltyReason", target = "penaltyReason")
    @Mapping(target = "trickAttempts",
        expression = "java(this.toValuesMap(request.trickAttempts()))")
    AddTrickAttemptsUseCase.AddTrickAttemptToTrickSerieCommand toCommand(TrickAttemptRequest request);

    @Mapping(source = "attempt.id", target = "id")
    @Mapping(source = "attempt.trickSerieId", target = "trickSerieId")
    @Mapping(source = "attempt.trickType", target = "type")
    @Mapping(target = "attempt.penalties", expression = "java(metricsMapper.toResponses(attempt.penalties))")
    TrickAttemptResponse toResponse(TrickAttempt attempt);

    @Mapping(source = "trickAttemptsWithScore.penaltyReason", target = "penaltyReason")
    @Mapping(target = "trickAttempts", expression = "java(this.toMap(trickAttemptsWithScore.trickAttempts()))")
    TrickAttemptsFullInfoResponse toResponse(TrickAttemptsWithScore trickAttemptsWithScore);

    default Map<TrickType, TrickAttemptResponse> toMap(Map<TrickType, TrickAttempt> trickAttempts) {
        Map<TrickType, TrickAttemptResponse> responseMap = new EnumMap<>(TrickType.class);
        trickAttempts.forEach((key, value) -> responseMap.put(key, toResponse(value)));

        return responseMap;
    }

    @Mapping(source = "request.penaltyReason", target = "penaltyReason")
    @Mapping(target = "attempts", expression = "java(this.toMapUpdated(request.attempts()))")
    UpdateTrickAttemptsResponse toResponse(TrickAttemptsUpdate request);

    UpdateTrickAttemptResponse toResponse(TrickAttemptForUpdate attemptForUpdate);

    default Map<TrickType, UpdateTrickAttemptResponse> toMapUpdated(Map<TrickType, TrickAttemptForUpdate> trickAttempts) {
        Map<TrickType, UpdateTrickAttemptResponse> responseMap = new EnumMap<>(TrickType.class);
        trickAttempts.forEach((key, value) -> responseMap.put(key, toResponse(value)));

        return responseMap;
    }

    @Mapping(source = "request.penaltyReason", target = "penaltyReason")
    @Mapping(target = "attempts", expression = "java(this.toMapUpdatedDomain(request.attempts()))")
    TrickAttemptsUpdate toDomain(UpdateTrickAttemptsRequest request);

    TrickAttemptForUpdate toDomain(UpdateTrickAttemptRequest attemptForUpdate);

    default Map<TrickType, TrickAttemptForUpdate> toMapUpdatedDomain(Map<TrickType, UpdateTrickAttemptRequest> trickAttempts) {
        Map<TrickType, TrickAttemptForUpdate> responseMap = new EnumMap<>(TrickType.class);
        trickAttempts.forEach((key, value) -> responseMap.put(key, toDomain(value)));

        return responseMap;
    }

    default Map<TrickType, PenaltyValues> toValuesMap(Map<TrickType, PenaltyMetricsRequest> trickAttempts) {
        Map<TrickType, PenaltyValues> valuesMap = new EnumMap<>(TrickType.class);
        trickAttempts.forEach((key, value) -> valuesMap.put(key, metricsMapper.toDomain(value)));

        return valuesMap;
    }

    default PenaltyReason mapToPenaltyReason(int number) {
        return PenaltyReason.of(number);
    }

    default int map(PenaltyReason status) {
        return status.ordinal();
    }
}
