package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.JumpingInfoEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface JumpingInfoJdbcRepository extends ListCrudRepository<JumpingInfoEntity, Long> {
}
