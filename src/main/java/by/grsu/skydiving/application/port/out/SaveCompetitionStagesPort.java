package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionStage;
import java.util.List;

public interface SaveCompetitionStagesPort {
    List<CompetitionStage> saveStages(Competition competition);
}
