package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.CompetitionCollegiumRefereeNotFoundException;
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
    public void deleteRefereeFromCompetitionByCompetitionCollegiumId(Long competitionCollegiumId, Long refereeId) {
        int affectedRowsCount =
            deleteRefereeFromCompetitionStagePort.deleteRefereeFromCompetitionByCompetitionCollegiumId(
                competitionCollegiumId,
                refereeId);
        if (affectedRowsCount == 0) {
            throw new CompetitionCollegiumRefereeNotFoundException(refereeId, competitionCollegiumId);
        }
    }
}
