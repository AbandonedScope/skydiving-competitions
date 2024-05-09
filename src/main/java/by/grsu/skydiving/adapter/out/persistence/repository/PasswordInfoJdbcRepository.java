package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.PassportInfoEntity;
import org.springframework.data.repository.CrudRepository;

public interface PasswordInfoJdbcRepository extends CrudRepository<PassportInfoEntity, Long> {
}
