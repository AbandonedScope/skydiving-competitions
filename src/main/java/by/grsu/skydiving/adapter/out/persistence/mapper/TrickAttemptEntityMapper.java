package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.TrickAttemptEntity;
import by.grsu.skydiving.application.domain.model.trickRefereeing.PenaltyMetrics;
import by.grsu.skydiving.application.domain.model.trickRefereeing.PenaltyType;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickType;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface TrickAttemptEntityMapper {
    TrickAttemptEntity toEntity(List<PenaltyMetrics> metrics, Long trickId);

    default Integer mapTrickType(TrickType trickType) {
        return trickType == null
            ? null
            : trickType.ordinal();
    }

    default TrickType mapSportDegree(Integer ordinal) {
        return ordinal == null
            ? null
            : TrickType.of(ordinal);
    }

    default Integer mapPenaltyType(PenaltyType penaltyType) {
        return penaltyType == null
            ? null
            : penaltyType.ordinal();
    }

    default PenaltyType mapPenaltyType(Integer ordinal) {
        return ordinal == null
            ? null
            : PenaltyType.of(ordinal);
    }
}
