package by.grsu.skydiving.adapter.out.persistence;

import by.grsu.skydiving.adapter.out.persistence.mapper.JumpingInfoEntityMapper;
import by.grsu.skydiving.adapter.out.persistence.repository.JumpingInfoJdbcRepository;
import by.grsu.skydiving.application.domain.model.jumping.JumpingInfo;
import by.grsu.skydiving.application.port.out.CreateCompetitionJumpingPort;
import by.grsu.skydiving.application.port.out.GetNextNumberOfJumpingPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CompetitionJumpingPersistenceAdapter
    implements CreateCompetitionJumpingPort, GetNextNumberOfJumpingPort {
    private final JumpingInfoJdbcRepository repository;
    private final JumpingInfoEntityMapper mapper;

    @Override
    public void create(JumpingInfo jumping) {
        var entity = mapper.toEntity(jumping);

        repository.save(entity);
    }

    @Override
    public int genNextNumberOfJumping(long competitionId, long skydiverId) {
        return repository.getNextNumberOfJumping(competitionId, skydiverId);
    }
}
