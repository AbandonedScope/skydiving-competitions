package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.TrickSerieEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface TrickSerieJdbcRepository extends ListCrudRepository<TrickSerieEntity, Long> {
}
