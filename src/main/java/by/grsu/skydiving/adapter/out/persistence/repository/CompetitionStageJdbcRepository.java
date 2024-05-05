package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionStageEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CompetitionStageJdbcRepository extends ListCrudRepository<CompetitionStageEntity, Long> {
    List<CompetitionStageEntity> findByCompetitionId(Long competitionId);
}
