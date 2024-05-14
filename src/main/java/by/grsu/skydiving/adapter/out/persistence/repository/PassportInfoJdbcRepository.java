package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.PassportInfoEntity;
import org.springframework.data.repository.CrudRepository;

public interface PassportInfoJdbcRepository extends CrudRepository<PassportInfoEntity, Long> {
}
