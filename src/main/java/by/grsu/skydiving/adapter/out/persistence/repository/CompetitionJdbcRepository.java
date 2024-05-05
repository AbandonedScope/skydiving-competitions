package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionEntity;
import org.springframework.data.repository.CrudRepository;

public interface CompetitionJdbcRepository extends CrudRepository<CompetitionEntity, Long> {
}
