package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionEntity;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface CompetitionEntityMapper {

    @Mapping(target = "address", source = "place.address")
    CompetitionEntity toEntity(Competition domain);

    @Mapping(target = "place.address", source = "address")
    Competition toDomain(CompetitionEntity entity);

    default CompetitionStatus map(int number) {
        return CompetitionStatus.of(number);
    }

    default int map(CompetitionStatus status) {
        return status.getNumber();
    }
}
