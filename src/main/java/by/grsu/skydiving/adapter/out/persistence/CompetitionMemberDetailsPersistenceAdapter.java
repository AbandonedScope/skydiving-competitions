package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionMemberDetailsEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.CompetitionMemberDetailsWithFullNameAndTeamName;
import by.grsu.skydiving.adapter.out.persistence.repository.CompetitionMemberDetailsJdbcRepository;
import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import by.grsu.skydiving.application.domain.model.competition.MembersOfCompetition;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import by.grsu.skydiving.application.port.in.GetCompetitionMemberByMemberNumberPort;
import by.grsu.skydiving.application.port.out.GetCompetitionMemberPort;
import by.grsu.skydiving.application.port.out.GetMembersOfCompetitionPort;
import by.grsu.skydiving.application.port.out.UpdateCompetitionMemberPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CompetitionMemberDetailsPersistenceAdapter
    implements GetMembersOfCompetitionPort, UpdateCompetitionMemberPort,
    GetCompetitionMemberByMemberNumberPort, GetCompetitionMemberPort {
    private final CompetitionMemberDetailsJdbcRepository repository;

    @Override
    public Optional<CompetitionMember> getByCompetitionIdAndMemberNumber(long competitionId, int memberNumber) {
        return repository.findByCompetitionIdAndMemberNumber(competitionId, memberNumber)
            .map(this::mapToCompetitionMember);
    }

    @Override
    public Optional<CompetitionMember> getByCompetitionIdAndSkydiverId(long competitionId, long skydiverId) {
        return repository.findByCompetitionIdAndSkydiverId(competitionId, skydiverId)
            .map(this::mapToCompetitionMember);
    }

    @Override
    public MembersOfCompetition getByCompetitionId(long competitionId) {
        var members = repository.findByCompetitionId(competitionId);

        Set<CompetitionMember> individuals = members.stream()
            .filter(CompetitionMemberDetailsWithFullNameAndTeamName::getIsIndividual)
            .map(this::mapToCompetitionMember)
            .collect(Collectors.toSet());

        List<Team> teams = members.stream()
            .filter(member -> !member.getIsIndividual())
            .collect(
                Collectors.groupingBy(CompetitionMemberDetailsWithFullNameAndTeamName::getTeamId)
            )
            .entrySet()
            .stream()
            .map(this::mapToTeam)
            .toList();

        return new MembersOfCompetition(new ArrayList<>(teams), individuals);
    }

    @Override
    public CompetitionMember update(CompetitionMember competitionMember) {
        var entity = CompetitionMemberDetailsEntity.builder()
            .id(competitionMember.id())
            .competitionId(competitionMember.competitionId())
            .teamId(competitionMember.teamId())
            .isJunior(competitionMember.isJunior())
            .memberNumber(competitionMember.memberNumber())
            .skydiverId(competitionMember.skydiverId())
            .build();

        entity = repository.save(entity);

        return mapToCompetitionMember(entity)
            .withName(competitionMember.name());
    }

    private Team mapToTeam(Entry<Long, List<CompetitionMemberDetailsWithFullNameAndTeamName>> entry) {
        Long teamId = entry.getKey();
        List<CompetitionMemberDetailsWithFullNameAndTeamName> members = entry.getValue();
        String teamName = members.get(0).getTeamName();

        return new Team(
            teamId,
            teamName,
            mapToCompetitionMember(members)
        );
    }

    private CompetitionMember mapToCompetitionMember(CompetitionMemberDetailsWithFullNameAndTeamName member) {
        return CompetitionMember.builder()
            .id(member.getId())
            .teamId(member.getTeamId())
            .competitionId(member.getCompetitionId())
            .memberNumber(member.getMemberNumber())
            .skydiverId(member.getSkydiverId())
            .isJunior(member.getIsJunior())
            .name(
                new FullName(
                    member.getFirstName(),
                    member.getSecondName(),
                    member.getPatronymic()
                )
            )
            .build();
    }

    private CompetitionMember mapToCompetitionMember(CompetitionMemberDetailsEntity member) {
        return CompetitionMember.builder()
            .id(member.getId())
            .teamId(member.getTeamId())
            .competitionId(member.getCompetitionId())
            .memberNumber(member.getMemberNumber())
            .skydiverId(member.getSkydiverId())
            .isJunior(member.getIsJunior())
            .build();
    }

    private Set<CompetitionMember> mapToCompetitionMember(
        List<CompetitionMemberDetailsWithFullNameAndTeamName> members) {
        return members.stream()
            .map(this::mapToCompetitionMember)
            .collect(Collectors.toSet());
    }
}
