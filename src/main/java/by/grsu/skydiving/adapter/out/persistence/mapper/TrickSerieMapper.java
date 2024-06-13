package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.TrickSerieEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.RefereeingProjection;
import by.grsu.skydiving.application.domain.model.trickRefereeing.Refereeing;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickRefereeing;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickRefereeingFullInfo;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickSerie;
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
    @Mapping(target = "skydiverNumber", source = "skydiverNumber")
    @Mapping(target = "serieNumber", source = "info.serieNumber")
    @Mapping(target = "roundNumber", source = "info.roundNumber")
    TrickRefereeing toDomain(List<TrickSerie> trickSeries, TrickRefereeingFullInfo info, Integer skydiverNumber);

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
}
