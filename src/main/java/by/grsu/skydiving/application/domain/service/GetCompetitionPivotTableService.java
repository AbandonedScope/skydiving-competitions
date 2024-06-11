package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;
import by.grsu.skydiving.application.domain.model.pivot.CompetitionPivotTable;
import by.grsu.skydiving.application.domain.model.pivot.CompetitionPivotTable.CompetitionPivotTableBuilder;
import by.grsu.skydiving.application.domain.model.pivot.JumpingShortInfo;
import by.grsu.skydiving.application.domain.model.pivot.MemberInfo;
import by.grsu.skydiving.application.domain.model.pivot.PivotTeamResult;
import by.grsu.skydiving.application.port.in.GetCompetitionPivotTableUseCase;
import by.grsu.skydiving.application.port.in.GetCompetitionUseCase;
import by.grsu.skydiving.application.port.out.GetJumpingOfAllMembersCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetCompetitionPivotTableService implements GetCompetitionPivotTableUseCase {
    private final GetCompetitionUseCase getCompetitionUseCase;
    private final GetJumpingOfAllMembersCompetitionPort getJumpingOfAllMembersCompetitionPort;
    //private final GetAcrobaticsOfAllMembersCompetitionPort getAcrobaticsOfAllMembersCompetitionPort;

    @Override
    public CompetitionPivotTable getTable(long competitionId) {
        Competition competition = getCompetitionUseCase.getCompetition(competitionId);
        CompetitionPivotTableBuilder tableBuilder = CompetitionPivotTable.builder()
            .competitionId(competition.getId())
            .competitionName(competition.getName());

        Map<Long, List<JumpingInfo>> jumpingByMemberId =
            getJumpingOfAllMembersCompetitionPort.getAllJumping(competitionId).stream()
                .collect(Collectors.groupingBy(
                    JumpingInfo::competitionMemberDetailsId
                ));

        List<MemberInfo> teamsMembersInfos = competition.getTeams().stream()
            .flatMap(team -> getMembersOfTeam(team, jumpingByMemberId))
            .toList();

        List<MemberInfo> individualsMembersInfos = competition.getIndividuals().stream()
            .map(individual -> mapMemberInfo(individual, jumpingByMemberId))
            .toList();

        rankMembersByJumpingAccuracy(teamsMembersInfos, individualsMembersInfos);
        rankMembersByAcrobatics(teamsMembersInfos, individualsMembersInfos);

        List<PivotTeamResult> pivotTeamResults = buildTeamResults(competition.getTeams(), teamsMembersInfos);
        rankTeamsByJumpingAccuracy(pivotTeamResults);

        return tableBuilder
            .competitionId(competitionId)
            .competitionName(competition.getName())
            .teams(pivotTeamResults)
            .individuals(individualsMembersInfos)
            .build();
    }

    private void rankMembersByAcrobatics(List<MemberInfo> teamsMembersInfos, List<MemberInfo> individualsMembersInfos) {
        //todo Заполнить метод логикой
    }

    private void rankMembersByJumpingAccuracy(List<MemberInfo> teamsMembersInfos,
                                              List<MemberInfo> individualsMembersInfos) {
        List<MemberInfo> allMembersSortedByJumping =
            Stream.concat(teamsMembersInfos.stream(), individualsMembersInfos.stream())
                .filter(memberInfo -> memberInfo.getJumpingSum() != null)
                .sorted()
                .toList();

        for (int i = 0; i < allMembersSortedByJumping.size(); i++) {
            MemberInfo memberInfo = allMembersSortedByJumping.get(i);

            memberInfo.setJumpingCompetitionRank(i + 1);
        }
    }

    private void rankTeamsByJumpingAccuracy(List<PivotTeamResult> teamResults) {
        teamResults.stream()
            .filter(teamResult -> teamResult.getJumpingSum() != null)
            .sorted()
            .reduce(
                1,
                (rank, teamResult) -> {
                    teamResult.setJumpingCompetitionRank(rank);
                    return 1;
                },
                Integer::sum
            );
    }

    private List<PivotTeamResult> buildTeamResults(List<Team> teams, List<MemberInfo> teamsMembersInfos) {
        Map<Long, List<MemberInfo>> membersByTeamId = teamsMembersInfos.stream()
            .collect(Collectors.groupingBy(
                MemberInfo::getTeamId
            ));

        return teams.stream()
            .map(
                team -> buildTeamResult(
                    team,
                    membersByTeamId.getOrDefault(team.id(), List.of())
                )
            )
            .sorted()
            .toList();
    }

    private PivotTeamResult buildTeamResult(Team team, List<MemberInfo> membersInfos) {
        List<Integer> sums = membersInfos.stream()
            .map(MemberInfo::getJumpingSum)
            .filter(Objects::nonNull)
            .toList();

        Integer teamJumpingSum = null;
        if (!sums.isEmpty()) {
            teamJumpingSum = sums.stream()
                .reduce(0, Integer::sum);
        }

        return PivotTeamResult.builder()
            .teamId(team.id())
            .teamName(team.name())
            .members(membersInfos)
            .jumpingSum(teamJumpingSum)
            //todo acrobatics sum
            .build();
    }

    private Stream<MemberInfo> getMembersOfTeam(Team team, Map<Long, List<JumpingInfo>> jumpingByMemberId) {
        return team.members().stream()
            .map(member -> mapMemberInfo(team, member, jumpingByMemberId));
    }

    private MemberInfo mapMemberInfo(Team team, CompetitionMember member,
                                     Map<Long, List<JumpingInfo>> jumpingByMemberId) {
        return mapMemberInfo(member, jumpingByMemberId).toBuilder()
            .teamId(team.id())
            .teamName(team.name())
            .build();
    }

    private MemberInfo mapMemberInfo(CompetitionMember member, Map<Long, List<JumpingInfo>> jumpingByMemberId) {
        long memberId = member.id();

        List<JumpingInfo> jumping = jumpingByMemberId.getOrDefault(memberId, List.of());

        List<JumpingShortInfo> jumpingShortInfos = jumping.stream()
            .map(jumpingInfo -> JumpingShortInfo.builder()
                .jumpingId(jumpingInfo.id())
                .number(jumpingInfo.number())
                .accuracy(jumpingInfo.accuracy())
                .build()
            )
            .toList();

        Integer jumpingSum = null;
        if (!jumping.isEmpty()) {
            jumpingSum = jumping.stream()
                .map(JumpingInfo::accuracy)
                .reduce(0, Integer::sum);
        }


        return MemberInfo.builder()
            .memberId(memberId)
            .name(member.name())
            .isJunior(member.isJunior())
            .memberNumber(member.memberNumber())
            .jumping(jumpingShortInfos)
            .jumpingSum(jumpingSum)
            .build();
    }
}
