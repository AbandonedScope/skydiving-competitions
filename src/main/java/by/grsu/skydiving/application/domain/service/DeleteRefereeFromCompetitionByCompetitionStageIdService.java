package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.CompetitionStageRefereeNotFoundException;
import by.grsu.skydiving.application.port.in.DeleteRefereeFromCompetitionStageUseCase;
import by.grsu.skydiving.application.port.out.DeleteRefereeFromCompetitionStagePort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteRefereeFromCompetitionByCompetitionStageIdService
    implements DeleteRefereeFromCompetitionStageUseCase {
    private final DeleteRefereeFromCompetitionStagePort deleteRefereeFromCompetitionStagePort;

    @Override
    public void deleteRefereeFromCompetitionByCompetitionStageId(Long competitionStageId, Long refereeId) {
        int affectedRowsCount =
            deleteRefereeFromCompetitionStagePort.deleteRefereeFromCompetitionByCompetitionStageId(competitionStageId,
                refereeId);
        if (affectedRowsCount == 0) {
            throw new CompetitionStageRefereeNotFoundException(refereeId, competitionStageId);
        }
    }
}
