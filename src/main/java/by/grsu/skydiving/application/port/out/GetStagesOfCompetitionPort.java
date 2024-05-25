package by.grsu.skydiving.application.port.out;

import generated.tables.CompetitionStage;
import java.util.List;

public interface GetStagesOfCompetitionPort {
    List<CompetitionStage> getByCompetitionId(long competitionId);
}
