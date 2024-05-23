package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionMemberDetailsEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.TeamEntity;
import by.grsu.skydiving.adapter.out.persistence.mapper.TeamEntityMapper;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.port.out.ExistsTeamByNamePort;
import by.grsu.skydiving.application.port.out.SaveCompetitionTeamsPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@PersistenceAdapter
@RequiredArgsConstructor
public class TeamPersistenceAdapter implements SaveCompetitionTeamsPort,
        ExistsTeamByNamePort {
    private final TeamJdbcRepository teamRepository;
    private final CompetitionMemberDetailsJdbcRepository membersRepository;
    private final TeamEntityMapper mapper;

    @Override
    @Transactional
    public List<Team> saveTeams(Competition competition) {
        long competitionId = competition.getId();

        List<Team> unsavedTeams = competition.getTeams();
        List<TeamEntity> teamsEntities = mapper.toEntities(unsavedTeams);
        teamsEntities = teamRepository.saveAll(teamsEntities);
        List<Team> saveTeams = new ArrayList<>();
        for (TeamEntity teamEntity : teamsEntities) {
            Team unsavedTeam = unsavedTeams.stream()
                    .filter(team -> team.name().equals(teamEntity.getName()))
                    .findAny()
                    .get();

            saveTeams.add(unsavedTeam.withId(teamEntity.getId()));
        }

        List<CompetitionMemberDetailsEntity> members = saveTeams.stream()
                .flatMap(team -> mapper.toMembers(team, competitionId).stream())
                .toList();
        members = saveMembers(members);

        return mapToDomains(teamsEntities, members);
    }

    @Override
    public boolean exists(String teamName) {
        return teamRepository.existsByName(teamName);
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
