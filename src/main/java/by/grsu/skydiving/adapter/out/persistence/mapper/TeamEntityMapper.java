package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionMemberDetailsEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.TeamEntity;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.domain.model.competition.TeamMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface TeamEntityMapper {
    TeamMember toDomain(CompetitionMemberDetailsEntity entity);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "members", source = "members")
    Team toDomain(TeamEntity entity, Set<CompetitionMemberDetailsEntity> members);

    TeamEntity toEntity(Team domain);

    List<TeamEntity> toEntities(List<Team> domains);

    default List<CompetitionMemberDetailsEntity> toMembers(Team team, long competitionId) {
        long teamId = team.id();
        List<CompetitionMemberDetailsEntity> members = new ArrayList<>();
        for (TeamMember member : team.members()) {
            var memberDetails = CompetitionMemberDetailsEntity.builder()
                    .skydiverId(member.skydiverId())
                    .isJunior(false)
                    .competitionId(competitionId)
                    .teamId(teamId)
                    .memberNumber(member.memberNumber())
                    .build();

            members.add(memberDetails);
        }

        return members;
    }
}
