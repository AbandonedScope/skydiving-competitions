package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.projections.CollegiumRefereeProjection;
import by.grsu.skydiving.application.domain.model.competition.CollegiumReferee;
import by.grsu.skydiving.application.domain.model.competition.RefereeCategory;
import by.grsu.skydiving.application.domain.model.competition.RefereeGroups;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN)

public interface RefereeEntityMapper {
    @Mapping(target = "referee.info.name.firstName", source = "firstName")
    @Mapping(target = "referee.info.name.secondName", source = "secondName")
    @Mapping(target = "referee.info.name.patronymic", source = "patronymic")
    @Mapping(target = "workPerformed", source = "workPerformed")
    @Mapping(target = "referee.category", source = "category")
    @Mapping(target = "referee.id", source = "id")
    CollegiumReferee toDomain(CollegiumRefereeProjection entity);

    default short map(RefereeCategory category) {
        return (short) category.ordinal();
    }

    default RefereeCategory map(short role) {
        return RefereeCategory.valueOf(role);
    }

    default RefereeGroups toDomain(List<CollegiumRefereeProjection> referees) {
        Set<CollegiumReferee> mainCollegium = extractCollegiumReferee(referees, CollegiumRefereeProjection::getIsMainCollegium);
        Set<CollegiumReferee> collegium = extractCollegiumReferee(referees, collegiumReferee -> !collegiumReferee.getIsMainCollegium());

        return new RefereeGroups(mainCollegium, collegium);
    }

    default Set<CollegiumReferee> extractCollegiumReferee(List<CollegiumRefereeProjection> trans, Predicate<CollegiumRefereeProjection> filter) {
        return trans.stream().filter(filter).map(this::toDomain).collect(Collectors.toSet());
    }
}
