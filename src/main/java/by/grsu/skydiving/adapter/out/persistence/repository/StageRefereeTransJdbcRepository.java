package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.StageRefereeTransEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface StageRefereeTransJdbcRepository extends ListCrudRepository<StageRefereeTransEntity, Long> {
}
