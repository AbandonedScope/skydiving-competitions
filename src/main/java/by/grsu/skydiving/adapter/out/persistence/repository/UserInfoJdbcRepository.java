package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.UserInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.projections.UserInfoWithoutCredentials;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserInfoJdbcRepository extends ListCrudRepository<UserInfoEntity, Integer> {
    Optional<UserInfoEntity> findByLogin(String login);

    Optional<UserInfoWithoutCredentials> findByUserId(Integer userId);
}
