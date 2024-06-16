package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.repository.CollegiumRefereeTransJdbcRepository;
import by.grsu.skydiving.application.port.out.DeleteRefereeFromCompetitionCollegiumPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CollegiumRefereeTransAdapter implements DeleteRefereeFromCompetitionCollegiumPort {
    private final CollegiumRefereeTransJdbcRepository collegiumRefereeTransJdbcRepository;

    @Override
    public int deleteFromCompetition(Long competitionId, Long refereeId) {
        return collegiumRefereeTransJdbcRepository
            .deleteByCompetitionIdAndRefereeId(
                competitionId,
                refereeId
            );
    }
}
