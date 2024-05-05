package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionStageEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.StageRefereeTransEntity;
import by.grsu.skydiving.application.domain.model.RefereeCollegium;
import by.grsu.skydiving.application.domain.model.competition.CollegiumReferee;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionStage;
import by.grsu.skydiving.application.domain.model.competition.CompetitionStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface CompetitionEntityMapper {

    @Mapping(target = "address", source = "place.address")
    CompetitionEntity toEntity(Competition domain);

    @Mapping(target = "id", source = "domain.id")
    @Mapping(target = "competitionId", source = "competitionId")
    @Mapping(target = "number", source = "domain.number")
    CompetitionStageEntity toEntity(CompetitionStage domain, Long competitionId);

    @Mapping(target = "competitionStageId", source = "stageId")
    @Mapping(target = "refereeId", source = "domain.referee.id")
    @Mapping(target = "workPerformed", source = "domain.workPerformed")
    @Mapping(target = "isMainCollegium", source = "isMainCollegium")
    StageRefereeTransEntity toEntity(CollegiumReferee domain, Long stageId, boolean isMainCollegium);

    default List<CompetitionStageEntity> toEntities(List<CompetitionStage> domains, Long competitionId) {
        return domains.stream()
                .map(domain -> toEntity(domain, competitionId))
                .toList();
    }

    @Mapping(target = "place.address", source = "address")
    Competition toDomain(CompetitionEntity entity);

    @Mapping(target = "place.address", source = "entity.address")
    @Mapping(target = "stages", source = "stageEntities")
    Competition toDomain(CompetitionEntity entity, List<CompetitionStageEntity> stageEntities);

    CompetitionStage toDomain(CompetitionStageEntity entity);

    @Mapping(target = "referee.id", source = "refereeId")
    CollegiumReferee toDomain(StageRefereeTransEntity entity);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "number", source = "entity.number")
    @Mapping(target = "mainCollegium", source = "mainCollegium")
    @Mapping(target = "collegium", source = "collegium")
    CompetitionStage toDomain(CompetitionStageEntity entity, RefereeCollegium mainCollegium, RefereeCollegium collegium);

    default CompetitionStage toDomain(CompetitionStageEntity entity, List<StageRefereeTransEntity> trans) {
        RefereeCollegium mainCollegium = new RefereeCollegium(
                extractCollegiumReferee(
                        trans,
                        StageRefereeTransEntity::isMainCollegium
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

    default Set<CollegiumReferee> extractCollegiumReferee(List<StageRefereeTransEntity> trans, Predicate<StageRefereeTransEntity> filter) {
        return trans.stream()
                .filter(filter)
                .map(this::toDomain)
                .collect(Collectors.toSet());
    }

    List<CompetitionStage> toDomains(List<CompetitionStageEntity> entities);

    default CompetitionStatus map(int number) {
        return CompetitionStatus.of(number);
    }

    default int map(CompetitionStatus status) {
        return status.getNumber();
    }
}
