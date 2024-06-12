package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.CollegiumRefereeTransEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionCollegiumEntity;
import by.grsu.skydiving.application.domain.model.RefereeCollegium;
import by.grsu.skydiving.application.domain.model.competition.CollegiumReferee;
import by.grsu.skydiving.application.domain.model.competition.CompetitionCollegium;
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
public interface CompetitionCollegiumEntityMapper {
    @Mapping(target = "id", source = "domain.id")
    @Mapping(target = "competitionId", source = "competitionId")
    CompetitionCollegiumEntity toEntity(CompetitionCollegium domain, Long competitionId);

    @Mapping(target = "competitionCollegiumId", source = "collegiumId")
    @Mapping(target = "refereeId", source = "domain.referee.id")
    @Mapping(target = "workPerformed", source = "domain.workPerformed")
    @Mapping(target = "isMainCollegium", source = "isMainCollegium")
    @Mapping(target = "refereeNumber", source = "domain.refereeNumber")
    CollegiumRefereeTransEntity toEntity(CollegiumReferee domain, Long collegiumId, boolean isMainCollegium);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "mainCollegium", source = "mainCollegium")
    @Mapping(target = "collegium", source = "collegium")
    CompetitionCollegium toDomain(CompetitionCollegiumEntity entity, RefereeCollegium mainCollegium,
                                  RefereeCollegium collegium);

    CompetitionCollegium toDomain(CompetitionCollegiumEntity entity);

    @Mapping(target = "referee.id", source = "refereeId")
    CollegiumReferee toDomain(CollegiumRefereeTransEntity entity);

    default CompetitionCollegium toDomain(CompetitionCollegiumEntity entity, List<CollegiumRefereeTransEntity> trans) {
        RefereeCollegium mainCollegium = new RefereeCollegium(
            extractCollegiumReferee(
                trans,
                CollegiumRefereeTransEntity::isMainCollegium
            )
        );

        RefereeCollegium collegium = new RefereeCollegium(
            extractCollegiumReferee(
                trans,
                referee -> !referee.isMainCollegium()
            )
        );

        return toDomain(entity, mainCollegium, collegium);
    }

    default Set<CollegiumReferee> extractCollegiumReferee(List<CollegiumRefereeTransEntity> trans,
                                                          Predicate<CollegiumRefereeTransEntity> filter) {
        return trans.stream()
            .filter(filter)
            .map(this::toDomain)
            .collect(Collectors.toSet());
    }
}
