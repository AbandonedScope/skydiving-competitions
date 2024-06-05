package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.TrickSerieEntity;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickRefereeing;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickRefereeingFullInfo;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickSerie;
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

public interface TrickSerieMapper {
    @Mapping(target = "refereeNumber", source = "refereeId")
    TrickSerie toDomain(TrickSerieEntity entity);
    List<TrickSerie> toDomains(List<TrickSerieEntity> entities);

    @Mapping(target = "trickSeries", source = "trickSeries")
    @Mapping(target = "skydiverNumber", source = "skydiverNumber")
    @Mapping(target = "serieNumber", source = "info.serieNumber")
    @Mapping(target = "roundNumber", source = "info.roundNumber")
    TrickRefereeing toDomain(List<TrickSerie> trickSeries, TrickRefereeingFullInfo info, Integer skydiverNumber);
}
