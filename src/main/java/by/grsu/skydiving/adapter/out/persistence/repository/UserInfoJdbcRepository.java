package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.UserInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.UserInfoWithoutCredentials;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserInfoJdbcRepository extends ListCrudRepository<UserInfoEntity, Long> {
    Optional<UserInfoEntity> findByLogin(String login);

    Optional<UserInfoWithoutCredentials> findByUserId(Long userId);

    @Modifying
    @Query("""
            update user_info
            set is_deleted = :deleted
            where id = :userId
            """)
    void updateByIdSetDeleted(long userId, boolean deleted);

    boolean existsByLogin(String login);
}
