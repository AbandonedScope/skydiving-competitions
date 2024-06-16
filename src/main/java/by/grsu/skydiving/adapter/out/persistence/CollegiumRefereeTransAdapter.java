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
    public int deleteFromCompetitionCollegium(Long competitionCollegiumId, Long refereeId) {
        return collegiumRefereeTransJdbcRepository
            .deleteByCompetitionCollegiumIdAndRefereeId(
                competitionCollegiumId,
                refereeId
            );
    }
}
