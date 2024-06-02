package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.JumpingInfoEntity;
import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface JumpingInfoEntityMapper {
    @Mapping(target = "id", source = "id")
    JumpingInfoEntity toEntity(JumpingInfo domain);

    @Mapping(target = "id", source = "id")
    JumpingInfo toDomain(JumpingInfoEntity entity);
}
