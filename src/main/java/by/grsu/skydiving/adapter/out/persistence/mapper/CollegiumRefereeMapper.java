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
    @Mapping(target = "referee.info.firstName", source = "refereeEntity.userInfoEntity.firstName")
    @Mapping(target = "referee.info.secondName", source = "refereeEntity.userInfoEntity.secondName")
    @Mapping(target = "referee.info.patronymic", source = "refereeEntity.userInfoEntity.patronymic")
    @Mapping(target = "workPerformed", source = "workPerformed")
    @Mapping(target = "referee.category", source = "refereeEntity.category")
    List<CollegiumReferee> toDomains(List<CollegiumRefereeProjection> entities);


    default short map(RefereeCategory category) {
        return (short) category.ordinal();
    }

    default RefereeCategory map(short role) {
        return RefereeCategory.valueOf(role);
    }
}
