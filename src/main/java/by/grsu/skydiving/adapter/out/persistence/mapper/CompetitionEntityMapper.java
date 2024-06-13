package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionCollegiumEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionEntity;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionShortInfo;
import by.grsu.skydiving.application.domain.model.competition.CompetitionStatus;
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
public interface CompetitionEntityMapper {

    @Mapping(target = "address", source = "place.address")
    CompetitionEntity toEntity(Competition domain);

    @Mapping(target = "place.address", source = "address")
    Competition toDomain(CompetitionEntity entity);

    @Mapping(target = "place.address", source = "address")
    CompetitionShortInfo toDomainShortInfo(CompetitionEntity entity);

    List<CompetitionShortInfo> toDomainShortInfos(List<CompetitionEntity> entity);

    @Mapping(target = "place.address", source = "entity.address")
    @Mapping(target = "collegium", source = "competitionCollegiumEntity")
    @Mapping(target = "id", source = "entity.id")
    Competition toDomain(CompetitionEntity entity, CompetitionCollegiumEntity competitionCollegiumEntity);

    default CompetitionStatus map(Integer number) {
        return CompetitionStatus.of(number);
    }

    default Integer map(CompetitionStatus status) {
        return status.getNumber();
    }
}
