package by.grsu.skydiving.adapter.out.persistence;

import static generated.Tables.COMPETITION_MEMBER_DETAIL;

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
import by.grsu.skydiving.application.port.out.GetNextMemberNumberAndIncrementPort;
import by.grsu.skydiving.application.port.out.SaveCompetitionTeamsPort;
import by.grsu.skydiving.application.port.out.SaveIndividualsPort;
import by.grsu.skydiving.application.port.out.SaveTeamPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
public class TeamPersistenceAdapter implements SaveCompetitionTeamsPort,
    ExistsTeamByNamePort, SaveTeamPort, DeleteTeamFromCompetitionPort,
    SaveIndividualsPort {
    private final TeamJdbcRepository teamRepository;
    private final CompetitionMemberDetailsJdbcRepository membersRepository;
    private final GetNextMemberNumberAndIncrementPort getNextMemberNumberAndIncrementPort;
    private final DSLContext dslContext;
    private final TeamEntityMapper mapper;

    @Override
    @Transactional
    public List<Team> saveTeams(Competition competition) {
        return competition.getTeams()
            .stream()
            .map(team -> saveTeam(team, competition.getId()))
            .toList();
    }

    @Override
    @Transactional
    public Team saveTeam(Team team, long competitionId) {
        TeamEntity teamEntity = mapper.toEntity(team);

        teamEntity = teamRepository.save(teamEntity);
        long teamId = teamEntity.getId();
        List<Long> skydiversOfTheTeamIds = team.members().stream()
            .map(CompetitionMember::skydiverId)
            .toList();

        dslContext.delete(COMPETITION_MEMBER_DETAIL)
            .where(COMPETITION_MEMBER_DETAIL.COMPETITION_ID.eq(competitionId)
                .and(COMPETITION_MEMBER_DETAIL.TEAM_ID.eq(teamId))
                .and(COMPETITION_MEMBER_DETAIL.SKYDIVER_ID.notIn(skydiversOfTheTeamIds)))
            .execute();

        List<Long> skydiversRemainingInTeamIds = membersRepository.findByTeamId(teamEntity.getId()).stream()
            .map(CompetitionMemberDetailsEntity::getSkydiverId)
            .toList();

        List<CompetitionMember> newTeamMembers = team.members().stream()
            .filter(member -> !skydiversRemainingInTeamIds.contains(member.skydiverId()))
            .toList();

        var members = newTeamMembers.stream()
            .map(member -> member.toBuilder()
                .teamId(teamId)
                .competitionId(competitionId)
                .memberNumber(getNextMemberNumberAndIncrementPort.getAndIncrement(competitionId))
                .build()
            )
            .collect(Collectors.toSet());

        List<CompetitionMemberDetailsEntity> membersEntities = mapper.toMembersEntities(members);
        membersEntities = saveMembers(membersEntities);

        return mapper.toDomain(teamEntity, membersEntities);
    }

    @Override
    public void saveIndividuals(Set<CompetitionMember> individuals, long competitionId) {
        List<Long> individualSkydiversOfCompetitionIds = individuals.stream()
            .map(CompetitionMember::skydiverId)
            .toList();

        dslContext.delete(COMPETITION_MEMBER_DETAIL)
            .where(COMPETITION_MEMBER_DETAIL.COMPETITION_ID.eq(competitionId)
                .and(COMPETITION_MEMBER_DETAIL.TEAM_ID.isNull())
                .and(COMPETITION_MEMBER_DETAIL.SKYDIVER_ID.notIn(individualSkydiversOfCompetitionIds)))
            .execute();

        List<Long> individualSkydiversRemainingInCompetitionIds =
            membersRepository.findIndividualsByCompetitionId(competitionId).stream()
                .map(CompetitionMemberDetailsEntity::getSkydiverId)
                .toList();

        List<CompetitionMemberDetailsEntity> newIndividuals = individuals.stream()
            .filter(individual -> !individualSkydiversRemainingInCompetitionIds.contains(individual.skydiverId()))
            .map(individual -> CompetitionMemberDetailsEntity.builder()
                .skydiverId(individual.skydiverId())
                .isJunior(false)
                .competitionId(competitionId)
                .memberNumber(getNextMemberNumberAndIncrementPort.getAndIncrement(competitionId))
                .build()
            )
            .toList();

        saveMembers(newIndividuals);
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
}
