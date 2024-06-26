package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.RefereeEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.CollegiumRefereeProjection;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.RefereeProjection;
import by.grsu.skydiving.application.domain.model.competition.CollegiumReferee;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.domain.model.competition.RefereeCategory;
import by.grsu.skydiving.application.domain.model.competition.RefereeGroups;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface RefereeEntityMapper {
    @Mapping(target = "referee.name.firstName", source = "firstName")
    @Mapping(target = "referee.name.secondName", source = "secondName")
    @Mapping(target = "referee.name.patronymic", source = "patronymic")
    @Mapping(target = "workPerformed", source = "workPerformed")
    @Mapping(target = "referee.category", source = "category")
    @Mapping(target = "referee.id", source = "id")
    CollegiumReferee toDomain(CollegiumRefereeProjection entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "new", ignore = true)
    RefereeEntity toEntity(Referee domain);

    @Mapping(target = "name.firstName", source = "firstName")
    @Mapping(target = "name.secondName", source = "secondName")
    @Mapping(target = "name.patronymic", source = "patronymic")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "id", source = "id")
    Referee toDomain(RefereeProjection entities);

    List<Referee> toDomains(List<RefereeProjection> entities);

    Referee toDomain(RefereeEntity entity);

    default RefereeGroups toDomain(List<CollegiumRefereeProjection> referees) {
        Set<CollegiumReferee> mainCollegium = extractCollegiumReferee(
            referees,
            CollegiumRefereeProjection::getIsMainCollegium
        );

        Set<CollegiumReferee> collegium = extractCollegiumReferee(
            referees,
            collegiumReferee -> !collegiumReferee.getIsMainCollegium()
        );

        return new RefereeGroups(mainCollegium, collegium);
    }

    default Set<CollegiumReferee> extractCollegiumReferee(List<CollegiumRefereeProjection> trans,
                                                          Predicate<CollegiumRefereeProjection> filter) {
        return trans.stream()
            .filter(filter)
            .map(this::toDomain)
            .collect(Collectors.toSet());
    }

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
