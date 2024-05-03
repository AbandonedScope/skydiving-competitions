package by.grsu.skydiving.application.domain.model.competition;

import by.grsu.skydiving.application.domain.model.*;
import by.grsu.skydiving.application.domain.model.skydiver.Address;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class Competition {
    private final Integer id;
    private final String name;
    private final List<Team> teams;
    private final MainJudgeCollegium mainJudgeCollegium;
    private final JudgeCollegium judgeCollegium;
    private final LocalDate beginDate;
    private final LocalDate endDate;
    private final Address place;
    private final CompetitionStages stages;
}

