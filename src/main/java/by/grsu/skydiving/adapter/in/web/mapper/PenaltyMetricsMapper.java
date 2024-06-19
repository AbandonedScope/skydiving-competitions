package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.PenaltyMetricsRequest;
import by.grsu.skydiving.adapter.in.web.response.PenaltyMetricsResponse;
import by.grsu.skydiving.application.domain.model.trick.PenaltyMetrics;
import by.grsu.skydiving.application.domain.model.trick.PenaltyValues;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface PenaltyMetricsMapper {
    PenaltyValues toDomain(PenaltyMetricsRequest request);

    PenaltyMetricsResponse toResponse(PenaltyMetrics request);

    List<PenaltyMetricsResponse> toResponses(List<PenaltyMetrics> request);
}
