package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.TrickSerieEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.RefereeingProjection;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.TrickSerieShortInfoProjection;
import by.grsu.skydiving.application.domain.model.trick.PenaltyReason;
import by.grsu.skydiving.application.domain.model.trick.Refereeing;
import by.grsu.skydiving.application.domain.model.trick.TrickRefereeing;
import by.grsu.skydiving.application.domain.model.trick.TrickRefereeingFullInfo;
import by.grsu.skydiving.application.domain.model.trick.TrickSerie;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieInfoForUpdate;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieShortInfo;
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
public interface TrickSerieMapper {
    @Mapping(target = "refereeNumber", source = "refereeId")
    TrickSerie toDomain(TrickSerieEntity entity);

    List<TrickSerie> toDomains(List<TrickSerieEntity> entities);

    @Mapping(target = "trickSeries", source = "trickSeries")
    @Mapping(target = "skydiverNumber", source = "memberNumber")
    @Mapping(target = "serieNumber", source = "info.serieNumber")
    @Mapping(target = "roundNumber", source = "info.roundNumber")
    TrickRefereeing toDomain(List<TrickSerie> trickSeries, TrickRefereeingFullInfo info, Integer memberNumber);

    @Mapping(target = "trickSerieId", source = "trickSerieId")
    @Mapping(target = "roundNumber", source = "roundNumber")
    @Mapping(target = "serieNumber", source = "serieNumber")
    @Mapping(target = "competition.competitionId", source = "competitionId")
    @Mapping(target = "competition.name", source = "name")
    @Mapping(target = "competition.beginDate", source = "beginDate")
    @Mapping(target = "competition.endDate", source = "endDate")
    @Mapping(target = "competition.address", source = "address")
    Refereeing toDomain(RefereeingProjection projection);

    List<Refereeing> toRefereeingDomains(List<RefereeingProjection> projections);

    TrickSerieShortInfo toDomain(TrickSerieShortInfoProjection trickSerie);

    @Mapping(target = "trickSerieId", source = "id")
    TrickSerieInfoForUpdate toUpdatedSerieDomain(TrickSerieEntity entity);

    default PenaltyReason mapToPenaltyReason(int number) {
        return PenaltyReason.of(number);
    }

    default int map(PenaltyReason status) {
        return status.ordinal();
    }
}
