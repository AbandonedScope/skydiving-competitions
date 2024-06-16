package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.PassportInfoEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PassportInfoJdbcRepository extends CrudRepository<PassportInfoEntity, Long> {
    Optional<PassportInfoEntity> findBySkydiverId(Long skydiverId);
}
