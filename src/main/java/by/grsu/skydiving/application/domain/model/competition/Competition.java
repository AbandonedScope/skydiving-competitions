package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.exception.domain.CollegiumRefereeUpdateException;
import by.grsu.skydiving.application.domain.exception.domain.IndividualAlreadyPresentedInCompetitionException;
import by.grsu.skydiving.application.domain.exception.domain.TeamAlreadyPresentedInCompetitionException;
import by.grsu.skydiving.application.domain.exception.domain.TeamWasNotFound;
import by.grsu.skydiving.application.domain.exception.domain.TeamWithNameNotFoundException;
import by.grsu.skydiving.application.domain.exception.domain.TryToAddIndividualThatInTeamException;
import by.grsu.skydiving.application.domain.model.skydiver.Address;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Competition {
    private Long id;
    private String name;
    @Builder.Default
    private List<Team> teams = new ArrayList<>();
    private Set<CompetitionMember> individuals;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Address place;
    private CompetitionCollegium collegium;
    private CompetitionStatus status;

    public void addRefereeToCollegium(CollegiumReferee referee, boolean isMainCollegium) {
        if (this.collegium == null) {
            this.collegium = CompetitionCollegium.builder().build();
        }

        this.collegium.addReferee(referee, isMainCollegium);
    }

    public void updateCollegium(CompetitionCollegium updatedCollegium) {
        if (collegium == null
            || Objects.equals(collegium.id(), updatedCollegium.id())) {
            collegium = updatedCollegium;
        } else {
            throw new CollegiumRefereeUpdateException();
        }

        updateStatusToCreated();
    }

    public Team getTeamByName(String teamName) {
        return teams.stream()
            .filter(team -> team.name().equals(teamName))
            .findAny()
            .orElseThrow(() -> new TeamWithNameNotFoundException(teamName));
    }

    public void addTeam(Team team) {
        String teamName = team.name();
        if (isTeamPresent(teamName)) {
            throw new TeamAlreadyPresentedInCompetitionException(teamName, this.name);
        }

        teams.add(team);
        updateStatusToCreated();
    }

    public void addIndividual(SkydiverShortInfo skydiver, int memberNumber) {
        CompetitionMember individual = CompetitionMember.builder()
            .skydiverId(skydiver.id())
            .name(skydiver.name())
            .memberNumber(memberNumber)
            .build();

        if (individuals.stream()
            .anyMatch(ind -> ind.skydiverId().equals(skydiver.id()))
        ) {
            throw new IndividualAlreadyPresentedInCompetitionException(individual.skydiverId(), this.name);
        }

        Optional<Team> teamWithIndividualToAdd = teams.stream()
            .filter(team -> team.containsMember(individual))
            .findAny();

        if (teamWithIndividualToAdd.isPresent()) {
            Team team = teamWithIndividualToAdd.get();
            throw new TryToAddIndividualThatInTeamException(individual.skydiverId(), team.name(), this.name);
        }

        individuals.add(individual);
        updateStatusToCreated();
    }

    public void removeIndividual(long individualId) {
        individuals.removeIf(member -> member.skydiverId() == individualId);
    }

    public MembersOfCompetition getMembers() {
        return MembersOfCompetition.builder()
            .teams(teams)
            .individuals(individuals)
            .build();
    }

    public Team getTeamById(long teamId) {
        return teams.stream()
            .filter(team -> team.id() == teamId)
            .findFirst()
            .orElseThrow(TeamWasNotFound::new);
    }

    public CompetitionMember getIndividualById(long individualId) {
        return individuals.stream()
            .filter(individual -> individual.skydiverId() == individualId)
            .findFirst().orElseThrow();
    }

    public boolean canBeUpdated() {
        return status != CompetitionStatus.RUNNING
               && status != CompetitionStatus.COMPLETED;
    }

    public boolean canBeRefereed() {
        return status == CompetitionStatus.RUNNING;
    }

    public boolean canMoveMembers() {
        return status != CompetitionStatus.COMPLETED;
    }

    private boolean isTeamPresent(String teamName) {
        return teams.stream()
            .anyMatch(team -> team.name().equals(teamName));
    }

    private void updateStatusToCreated() {
        if (status == CompetitionStatus.INITIAL
            && (!teams.isEmpty()
                || (individuals != null && !individuals.isEmpty()))
            && collegium != null
        ) {
            status = CompetitionStatus.CREATED;
        }
    }
}

