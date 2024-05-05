package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionEntity;
import by.grsu.skydiving.adapter.out.persistence.mapper.CompetitionEntityMapper;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.port.out.SaveCompetitionPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CompetitionPersistenceAdapter implements SaveCompetitionPort {
    private final CompetitionJdbcRepository repository;
    private final CompetitionEntityMapper mapper;

    @Override
    public Competition save(Competition competition) {
        CompetitionEntity entity = mapper.toEntity(competition);
        entity = repository.save(entity);

        return mapper.toDomain(entity);
    }
}
