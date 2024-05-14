package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.TeamEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface TeamJdbcRepository extends ListCrudRepository<TeamEntity, Long> {
    boolean existsByName(String name);
}
