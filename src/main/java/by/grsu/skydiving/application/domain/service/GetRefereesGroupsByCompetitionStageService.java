package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.RefereesNotFoundException;
import by.grsu.skydiving.application.domain.model.competition.RefereeGroups;
import by.grsu.skydiving.application.port.in.GetRefereesGroupsByCompetitionStageIdUseCase;
import by.grsu.skydiving.application.port.out.FindRefereesPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetRefereesGroupsByCompetitionStageService implements GetRefereesGroupsByCompetitionStageIdUseCase {
    FindRefereesPort findRefereesPort;
    @Override
    public RefereeGroups findRefereesByCompetitionStageId(Long competitionStageId) {
        return findRefereesPort.findRefereesByCompetitionStageId(competitionStageId)
                .orElseThrow(RefereesNotFoundException::new);
    }
}
