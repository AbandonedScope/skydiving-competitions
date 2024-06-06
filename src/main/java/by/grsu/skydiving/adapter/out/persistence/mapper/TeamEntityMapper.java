package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionMemberDetailsEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.TeamEntity;
import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import by.grsu.skydiving.application.domain.model.competition.Team;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface TeamEntityMapper {
    CompetitionMember toDomain(CompetitionMemberDetailsEntity entity);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "members", source = "members")
    Team toDomain(TeamEntity entity, Collection<CompetitionMemberDetailsEntity> members);

    TeamEntity toEntity(Team domain);

    CompetitionMemberDetailsEntity toEntity(CompetitionMember domain);

    List<CompetitionMemberDetailsEntity> toMembersEntities(Set<CompetitionMember> domain);

    List<TeamEntity> toEntities(List<Team> domains);
}
