package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionStageEntity;
import java.util.List;
import org.springframework.data.repository.ListCrudRepository;

public interface CompetitionStageJdbcRepository extends ListCrudRepository<CompetitionStageEntity, Long> {
    List<CompetitionStageEntity> findByCompetitionId(Long competitionId);
}
