package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.exception.business.CompetitionStagesLimitExceededException;
import by.grsu.skydiving.application.domain.exception.domain.CompetitionStageNumberIncorrectException;
import by.grsu.skydiving.application.domain.exception.domain.IndividualAlreadyPresentedInCompetitionException;
import by.grsu.skydiving.application.domain.exception.domain.TeamAlreadyPresentedInCompetitionException;
import by.grsu.skydiving.application.domain.exception.domain.TeamWithNameNotFoundException;
import by.grsu.skydiving.application.domain.exception.domain.TryToAddIndividualThatInTeamException;
import by.grsu.skydiving.application.domain.model.skydiver.Address;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    @Builder.Default
    private List<CompetitionStage> stages = new ArrayList<>();
    private Integer numberOfStages;
    private CompetitionStatus status;

    public void addStage(CompetitionStage stage) {
        int nextStageNumber = stages.size() + 1;
        if (stage.number() != nextStageNumber) {
            throw new CompetitionStageNumberIncorrectException(stage.number(), nextStageNumber);
        }

        if (nextStageNumber > numberOfStages) {
            throw new CompetitionStagesLimitExceededException(numberOfStages, nextStageNumber);
        }

        stages.add(stage);
    }

    public CompetitionStage getStage(int stageNumber) {
        return stages.stream()
            .filter(stage -> stage.number() == stageNumber)
            .findFirst()
            .orElseThrow();
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
    }

    public void addIndividual(SkydiverShortInfo skydiver, int memberNumber) {
        CompetitionMember individual = CompetitionMember.builder()
            .skydiverId(skydiver.id())
            .name(skydiver.name())
            .memberNumber(memberNumber)
            .build();

        if (individuals.contains(individual)) {
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
    }

    public MembersOfCompetition getMembers() {
        return MembersOfCompetition.builder()
            .teams(teams)
            .individuals(individuals)
            .build();
    }

    public boolean canBeUpdated() {
        return status != CompetitionStatus.RUNNING
               && status != CompetitionStatus.COMPLETED;
    }

    private boolean isTeamPresent(String teamName) {
        return teams.stream()
            .anyMatch(team -> team.name().equals(teamName));
    }
}

