package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.CompetitionCollegiumRefereeNotFoundException;
import by.grsu.skydiving.application.port.in.DeleteRefereeFromCompetitionCollegiumUseCase;
import by.grsu.skydiving.application.port.out.DeleteRefereeFromCompetitionCollegiumPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteRefereeFromCompetitionByCompetitionCollegiumIdService
    implements DeleteRefereeFromCompetitionCollegiumUseCase {
    private final DeleteRefereeFromCompetitionCollegiumPort deleteRefereeFromCompetitionCollegiumPort;

    @Override
    public void deleteByCollegiumId(Long competitionCollegiumId, Long refereeId) {
        int affectedRowsCount =
            deleteRefereeFromCompetitionCollegiumPort.deleteFromCompetitionCollegium(
                competitionCollegiumId,
                refereeId);
        if (affectedRowsCount == 0) {
            throw new CompetitionCollegiumRefereeNotFoundException(refereeId, competitionCollegiumId);
        }
    }
}
