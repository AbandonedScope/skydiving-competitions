package by.grsu.skydiving.application.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class Competition {
    private final int id;
    private final String name;
    private final List<Team> teams;
    private final MainJudgeCollegium mainJudgeCollegium;
    private final JudgeCollegium judgeCollegium;
    private final LocalDate beginDate;
    private final LocalDate endDate;
    private final Place place;
    private final CompetitionStages stages;

}

