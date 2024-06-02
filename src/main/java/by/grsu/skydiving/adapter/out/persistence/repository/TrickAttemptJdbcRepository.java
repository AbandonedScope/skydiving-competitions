package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.TrickAttemptEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface TrickAttemptJdbcRepository extends ListCrudRepository<TrickAttemptEntity, Long> {
}
