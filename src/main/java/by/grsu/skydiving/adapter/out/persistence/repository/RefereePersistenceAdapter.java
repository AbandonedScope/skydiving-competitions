package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.mapper.RefereeEntityMapper;
import by.grsu.skydiving.application.domain.model.competition.RefereeGroups;
import by.grsu.skydiving.application.port.out.DeleteRefereePort;
import by.grsu.skydiving.application.port.out.FindRefereesPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class RefereePersistenceAdapter implements FindRefereesPort, DeleteRefereePort {
    private final RefereeJdbcRepository refereeJdbcRepository;
    private final RefereeEntityMapper refereeEntityMapper;

    @Override
    public Optional<RefereeGroups> findRefereesByCompetitionStageId(Long competitionStageId) {
        return refereeJdbcRepository.findByCompetitionId(competitionStageId)
                .map(refereeEntityMapper::toDomain);
    }

    @Override
    public int deleteRefereeByRefereeId(Long refereeId) {
        return refereeJdbcRepository.deleteRefereeByRefereeId(refereeId);
    }
}
