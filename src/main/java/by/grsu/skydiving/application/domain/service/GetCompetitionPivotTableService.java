package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import by.grsu.skydiving.application.domain.model.competition.Team;
import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;
import by.grsu.skydiving.application.domain.model.pivot.AcrobaticsShortInfo;
import by.grsu.skydiving.application.domain.model.pivot.CompetitionPivotTable;
import by.grsu.skydiving.application.domain.model.pivot.JumpingShortInfo;
import by.grsu.skydiving.application.domain.model.pivot.MemberInfo;
import by.grsu.skydiving.application.domain.model.pivot.PivotTeamResult;
import by.grsu.skydiving.application.port.in.GetCompetitionPivotTableUseCase;
import by.grsu.skydiving.application.port.in.GetCompetitionUseCase;
import by.grsu.skydiving.application.port.out.GetAcrobaticsOfAllMembersCompetitionPort;
import by.grsu.skydiving.application.port.out.GetJumpingOfAllMembersCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.ObjIntConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetCompetitionPivotTableService implements GetCompetitionPivotTableUseCase {
    private final GetCompetitionUseCase getCompetitionUseCase;
    private final GetJumpingOfAllMembersCompetitionPort getJumpingOfAllMembersCompetitionPort;
    private final GetAcrobaticsOfAllMembersCompetitionPort getAcrobaticsOfAllMembersCompetitionPort;

    @Override
    public CompetitionPivotTable getTable(long competitionId) {
        Competition competition = getCompetitionUseCase.getCompetition(competitionId);

        Map<Long, List<JumpingInfo>> jumpingByMemberId =
            getJumpingOfAllMembersCompetitionPort.getAllJumping(competitionId).stream()
                .collect(Collectors.groupingBy(JumpingInfo::competitionMemberDetailsId));
        Map<Long, List<AcrobaticsShortInfo>> acrobaticsByMemberId =
            getAcrobaticsOfAllMembersCompetitionPort.getAcrobaticsOfAllMembers(competitionId).stream()
                .collect(Collectors.groupingBy(AcrobaticsShortInfo::competitionMemberDetailsId));

        CompetitionPivotTable competitionPivotTable =
            buildPivotTable(competition, jumpingByMemberId, acrobaticsByMemberId);

        rankCompetitionPivotTable(competitionPivotTable);

        return competitionPivotTable;
    }

    private CompetitionPivotTable buildPivotTable(Competition competition,
                                                  Map<Long, List<JumpingInfo>> jumpingByMemberId,
                                                  Map<Long, List<AcrobaticsShortInfo>> acrobaticsByMemberId) {
        List<MemberInfo> teamsMembersInfos = competition.getTeams().stream()
            .flatMap(team -> getMembersOfTeam(team, jumpingByMemberId, acrobaticsByMemberId))
            .toList();

        List<MemberInfo> individualsMembersInfos = competition.getIndividuals().stream()
            .map(individual -> mapMemberInfo(individual, jumpingByMemberId, acrobaticsByMemberId))
            .toList();

        Map<Long, List<MemberInfo>> membersByTeamId = teamsMembersInfos.stream()
            .collect(Collectors.groupingBy(MemberInfo::getTeamId));

        return CompetitionPivotTable.builder()
            .competitionId(competition.getId())
            .competitionName(competition.getName())
            .teams(buildTeamResults(competition.getTeams(), membersByTeamId))
            .individuals(individualsMembersInfos)
            .build();
    }


    private Stream<MemberInfo> getMembersOfTeam(Team team,
                                                Map<Long, List<JumpingInfo>> jumpingByMemberId,
                                                Map<Long, List<AcrobaticsShortInfo>> acrobaticsByMemberId) {
        return team.members().stream()
            .map(member -> mapMemberInfo(member, jumpingByMemberId, acrobaticsByMemberId).toBuilder()
                .teamName(team.name())
                .teamId(team.id())
                .build());
    }

    private MemberInfo mapMemberInfo(CompetitionMember member,
                                     Map<Long, List<JumpingInfo>> jumpingByMemberId,
                                     Map<Long, List<AcrobaticsShortInfo>> acrobaticsByMemberId) {
        List<JumpingInfo> jumping = jumpingByMemberId.getOrDefault(member.id(), List.of());
        List<AcrobaticsShortInfo> acrobatics = acrobaticsByMemberId.getOrDefault(member.id(), List.of());

        List<JumpingShortInfo> jumpingShortInfos = jumping.stream()
            .map(jumpingInfo -> JumpingShortInfo.builder()
                .jumpingId(jumpingInfo.id())
                .number(jumpingInfo.number())
                .accuracy(jumpingInfo.accuracy())
                .build()
            )
            .toList();

        return MemberInfo.builder()
            .memberId(member.id())
            .name(member.name())
            .isJunior(member.isJunior())
            .memberNumber(member.memberNumber())
            .jumping(jumpingShortInfos)
            .acrobatics(acrobatics)
            .build();
    }

    private List<PivotTeamResult> buildTeamResults(List<Team> teams, Map<Long, List<MemberInfo>> membersByTeamId) {

        return teams.stream()
            .map(team -> PivotTeamResult.builder()
                .teamId(team.id())
                .teamName(team.name())
                .members(membersByTeamId.getOrDefault(team.id(), List.of()))
                .build()
            )
            .toList();
    }

    private void rankCompetitionPivotTable(CompetitionPivotTable competitionPivotTable) {
        rankMembers(competitionPivotTable.teams(), competitionPivotTable.individuals());

        rankTeams(competitionPivotTable.teams());
    }

    private void rankMembers(List<PivotTeamResult> teamsMembersInfos,
                             List<MemberInfo> individualsMembersInfos) {
        rankMembersByJumpingAccuracy(teamsMembersInfos, individualsMembersInfos);
        rankMembersByAcrobatics(teamsMembersInfos, individualsMembersInfos);
    }

    private void rankMembersByJumpingAccuracy(List<PivotTeamResult> teamsMembersInfos,
                                              List<MemberInfo> individualsMembersInfos) {
        rankElements(
            Stream.concat(
                teamsMembersInfos.stream().flatMap(pivotTeamResult -> pivotTeamResult.getMembers().stream()),
                individualsMembersInfos.stream()
            ),
            memberInfo -> Objects.nonNull(memberInfo.getJumpingSum()),
            Comparator.comparingInt(MemberInfo::getJumpingSum),
            MemberInfo::setJumpingCompetitionRank
        );
    }

    private void rankMembersByAcrobatics(List<PivotTeamResult> teamsMembersInfos,
                                         List<MemberInfo> individualsMembersInfos) {
        rankElements(
            Stream.concat(
                teamsMembersInfos.stream().flatMap(pivotTeamResult -> pivotTeamResult.getMembers().stream()),
                individualsMembersInfos.stream()
            ),
            memberInfo -> Objects.nonNull(memberInfo.getAcrobaticsSum()),
            Comparator.comparingDouble(MemberInfo::getAcrobaticsSum),
            MemberInfo::setAcrobaticsCompetitionRank
        );
    }

    private void rankTeams(List<PivotTeamResult> teamResults) {
        rankTeamsByJumpingAccuracy(teamResults);
        rankTeamsByAcrobatics(teamResults);
    }

    private void rankTeamsByJumpingAccuracy(List<PivotTeamResult> teamResults) {
        rankElements(
            teamResults.stream(),
            teamResult -> Objects.nonNull(teamResult.getJumpingSum()),
            Comparator.comparingInt(PivotTeamResult::getJumpingSum),
            PivotTeamResult::setJumpingCompetitionRank
        );
    }

    private void rankTeamsByAcrobatics(List<PivotTeamResult> teamResults) {
        rankElements(
            teamResults.stream(),
            teamResult -> Objects.nonNull(teamResult.getAcrobaticsSum()),
            Comparator.comparingDouble(PivotTeamResult::getAcrobaticsSum),
            PivotTeamResult::setAcrobaticsCompetitionRank
        );
    }

    private <T> void rankElements(Stream<T> elements, Predicate<T> filter, Comparator<T> comparator,
                                  ObjIntConsumer<T> setRank) {
        elements.filter(filter)
            .sorted(comparator)
            .reduce(1, (rank, element) -> {
                setRank.accept(element, rank);
                return rank + 1;
            }, Integer::sum);
    }
}
