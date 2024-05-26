package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.competition.CompetitionStage;
import java.util.List;

public interface GetStagesOfCompetitionPort {
    List<CompetitionStage> getByCompetitionId(long competitionId);
}
