package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionMemberDetailsEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.TeamEntity;
import by.grsu.skydiving.adapter.out.persistence.mapper.TeamEntityMapper;
import by.grsu.skydiving.adapter.out.persistence.repository.CompetitionMemberDetailsJdbcRepository;
import by.grsu.skydiving.adapter.out.persistence.repository.TeamJdbcRepository;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.port.out.DeleteTeamFromCompetitionPort;
import by.grsu.skydiving.application.port.out.ExistsTeamByNamePort;
import by.grsu.skydiving.application.port.out.SaveCompetitionTeamsPort;
import by.grsu.skydiving.application.port.out.SaveIndividualsPort;
import by.grsu.skydiving.application.port.out.SaveTeamPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
public class TeamPersistenceAdapter implements SaveCompetitionTeamsPort,
    ExistsTeamByNamePort, SaveTeamPort, DeleteTeamFromCompetitionPort,
    SaveIndividualsPort {
    private final TeamJdbcRepository teamRepository;
    private final CompetitionMemberDetailsJdbcRepository membersRepository;
    private final TeamEntityMapper mapper;

    @Override
    @Transactional
    public List<Team> saveTeams(Competition competition) {
        List<Team> unsavedTeams = competition.getTeams();
        List<TeamEntity> teamsEntities = mapper.toEntities(unsavedTeams);
        teamsEntities = teamRepository.saveAll(teamsEntities);
        List<Team> saveTeams = new ArrayList<>();
        for (TeamEntity teamEntity : teamsEntities) {
            Team unsavedTeam = unsavedTeams.stream()
                .filter(team -> team.name().equals(teamEntity.getName()))
                .findAny()
                .orElseThrow();

            saveTeams.add(unsavedTeam.withId(teamEntity.getId()));
        }

        List<CompetitionMemberDetailsEntity> members = saveTeams.stream()
            .flatMap(team -> team.members().stream()
                .map(member -> member.withTeamId(team.id()))
            )
            .map(mapper::toEntity)
            .toList();
        members = saveMembers(members);

        return mapToDomains(teamsEntities, members);
    }

    @Override
    @Transactional
    public Team saveTeam(Team team, long competitionId) {
        TeamEntity teamEntity = mapper.toEntity(team);

        teamEntity = teamRepository.save(teamEntity);
        List<CompetitionMemberDetailsEntity> teamMembers = membersRepository.findByTeamId(teamEntity.getId());
        membersRepository.deleteAll(teamMembers);

        Long teamId = teamEntity.getId();
        var members = team.members().stream()
            .map(member -> member.withId(null))
            .map(member -> member.withTeamId(teamId))
            .map(member -> member.withCompetitionId(competitionId))
            .collect(Collectors.toSet());

        List<CompetitionMemberDetailsEntity> membersEntities = mapper.toMembersEntities(members);
        membersEntities = saveMembers(membersEntities);

        return mapper.toDomain(teamEntity, membersEntities);
    }

    @Override
    public void saveIndividuals(Set<CompetitionMember> individuals, long competitionId) {
        List<CompetitionMemberDetailsEntity> teamMembers =
            membersRepository.findIndividualsByCompetitionId(competitionId);
        membersRepository.deleteAll(teamMembers);

        List<CompetitionMemberDetailsEntity> entities = individuals.stream()
            .map(individual -> CompetitionMemberDetailsEntity.builder()
                .skydiverId(individual.skydiverId())
                .isJunior(false)
                .competitionId(competitionId)
                .memberNumber(individual.memberNumber())
                .build()
            )
            .toList();

        saveMembers(entities);
    }

    @Override
    public boolean exists(String teamName) {
        return teamRepository.existsByName(teamName);
    }

    @Override
    public void deleteTeamFromCompetition(long competitionId, long teamId) {
        List<CompetitionMemberDetailsEntity> members =
            membersRepository.findByTeamIdAndCompetitionId(teamId, competitionId);
        membersRepository.deleteAll(members);
    }

    private List<CompetitionMemberDetailsEntity> saveMembers(List<CompetitionMemberDetailsEntity> members) {
        return membersRepository.saveAll(members);
    }

    private List<Team> mapToDomains(List<TeamEntity> teams, List<CompetitionMemberDetailsEntity> members) {
        Map<Long, Set<CompetitionMemberDetailsEntity>> membersGroupedByTeamId = groupByTeamId(members);

        return teams.stream()
            .map(teamEntity -> {
                Long teamId = teamEntity.getId();
                Set<CompetitionMemberDetailsEntity> membersOfTeam = membersGroupedByTeamId.get(teamId);
                return mapper.toDomain(teamEntity, membersOfTeam);
            })
            .toList();
    }

    private Map<Long, Set<CompetitionMemberDetailsEntity>> groupByTeamId(List<CompetitionMemberDetailsEntity> members) {
        Map<Long, Set<CompetitionMemberDetailsEntity>> membersGroupedByTeamId = HashMap.newHashMap(5);

        for (CompetitionMemberDetailsEntity memberDetails : members) {
            long teamId = memberDetails.getTeamId();

            Set<CompetitionMemberDetailsEntity> membersOfTeam;
            if (!membersGroupedByTeamId.containsKey(teamId)) {
                membersOfTeam = new HashSet<>();
                membersGroupedByTeamId.put(teamId, membersOfTeam);
            } else {
                membersOfTeam = membersGroupedByTeamId.get(teamId);
            }

            membersOfTeam.add(memberDetails);
        }

        return membersGroupedByTeamId;
    }
}
