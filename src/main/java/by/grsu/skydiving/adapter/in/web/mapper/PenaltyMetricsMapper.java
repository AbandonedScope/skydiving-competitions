package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.PenaltyMetricsRequest;
import by.grsu.skydiving.adapter.in.web.response.PenaltyMetricsResponse;
import by.grsu.skydiving.application.domain.model.trickRefereeing.PenaltyMetrics;
import by.grsu.skydiving.application.domain.model.trickRefereeing.PenaltyValues;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface PenaltyMetricsMapper {
    PenaltyValues toDomain (PenaltyMetricsRequest request);

    PenaltyMetricsResponse toResponse (PenaltyMetrics request);
    List<PenaltyMetricsResponse> toResponses (List<PenaltyMetrics> request);
}
