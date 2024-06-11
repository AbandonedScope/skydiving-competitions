package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.pivot.CompetitionPivotTable;

public interface GetCompetitionPivotTableUseCase {
    CompetitionPivotTable getTable(long competitionId);
}
