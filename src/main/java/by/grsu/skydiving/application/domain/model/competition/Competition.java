package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.exception.business.CompetitionStagesLimitExceededException;
import by.grsu.skydiving.application.domain.exception.domain.CompetitionStageNumberIncorrectException;
import by.grsu.skydiving.application.domain.exception.domain.TeamAlreadyPresentedInCompetitionException;
import by.grsu.skydiving.application.domain.exception.domain.TeamWithNameNotFoundException;
import by.grsu.skydiving.application.domain.model.skydiver.Address;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    public boolean canBeUpdated() {
        return status != CompetitionStatus.RUNNING
                && status != CompetitionStatus.COMPLETED;
    }

    private boolean isTeamPresent(String teamName) {
        return teams.stream()
                .anyMatch(team -> team.name().equals(teamName));
    }
}

