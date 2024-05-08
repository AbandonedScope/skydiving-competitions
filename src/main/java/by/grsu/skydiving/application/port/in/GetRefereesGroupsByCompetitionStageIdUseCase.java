package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.competition.RefereeGroups;

public interface GetRefereesGroupsByCompetitionStageIdUseCase {
    RefereeGroups findRefereesByCompetitionStageId(Long competitionStageId);
}
