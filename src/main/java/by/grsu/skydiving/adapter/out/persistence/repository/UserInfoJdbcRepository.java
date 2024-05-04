package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.UserInfoEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserInfoJdbcRepository extends ListCrudRepository<UserInfoEntity, Integer> {
    Optional<UserInfoEntity> findByLoginAndPassword(String login, String password);
}
