package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionCollegiumEntity;
import java.util.Optional;
import org.springframework.data.repository.ListCrudRepository;

public interface CompetitionCollegiumJdbcRepository extends ListCrudRepository<CompetitionCollegiumEntity, Long> {
    Optional<CompetitionCollegiumEntity> findByCompetitionId(Long competitionId);
}
