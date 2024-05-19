package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.projection.CollegiumRefereeProjection;
import by.grsu.skydiving.application.domain.model.competition.CollegiumReferee;
import by.grsu.skydiving.application.domain.model.competition.RefereeCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface CollegiumRefereeMapper {
    @Mapping(target = "referee.name.firstName", source = "firstName")
    @Mapping(target = "referee.name.secondName", source = "secondName")
    @Mapping(target = "referee.name.patronymic", source = "patronymic")
    @Mapping(target = "workPerformed", source = "workPerformed")
    @Mapping(target = "referee.category", source = "category")
    CollegiumReferee toDomain(CollegiumRefereeProjection entities);

    List<CollegiumReferee> toDomains(List<CollegiumRefereeProjection> entities);

    default RefereeCategory mapRefereeCategory(Integer ordinal) {
        return ordinal == null
                ? null
                : RefereeCategory.of(ordinal);
    }

    default Integer mapRefereeCategory(RefereeCategory refereeCategory) {
        return refereeCategory == null
                ? null
                : refereeCategory.ordinal();
    }
}
