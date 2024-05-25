package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.projection.CompetitionMemberDetailsWithFullNameAndTeamName;
import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import by.grsu.skydiving.application.domain.model.competition.MembersOfCompetition;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import by.grsu.skydiving.application.port.out.GetMembersOfCompetitionPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CompetitionMemberDetailsPersistenceAdapter
    implements GetMembersOfCompetitionPort {
    private final CompetitionMemberDetailsJdbcRepository repository;

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
            .memberNumber(member.getMemberNumber())
            .skydiverId(member.getSkydiverId())
            .name(
                new FullName(
                    member.getFirstName(),
                    member.getSecondName(),
                    member.getPatronymic()
                )
            )
            .build();
    }

    private Set<CompetitionMember> mapToCompetitionMember(
        List<CompetitionMemberDetailsWithFullNameAndTeamName> members) {
        return members.stream()
            .map(this::mapToCompetitionMember)
            .collect(Collectors.toSet());
    }
}
