package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.SkydiverEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.SkydiverShortInfoProjection;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface SkydiverJdbcRepository extends CrudRepository<SkydiverEntity, Long> {

    @Query("""
        select skydiver.id,
               user_info.first_name,
               user_info.second_name,
               user_info.patronymic,
               skydiver.begin_of_sport_career,
               skydiver.sport_rank,
               skydiver.is_internal,
               skydiver.gender
        from skydiver_view as skydiver
            left join user_info on user_info.id = skydiver.id
        where
            skydiver.id = :skydiverId
        """)
    Optional<SkydiverShortInfoProjection> findByIdShort(long skydiverId);

    Optional<SkydiverEntity> findById(long skydiverId);

    @Query("""
        select exists(select 1
                      from skydiver_view as skydiver
                          left join user_info on user_info.id = skydiver.id
                      where
                          user_info.first_name = :firstName
                        and user_info.second_name = :secondName
                        and user_info.patronymic = :patronymic
                        and skydiver.birth_date = :birthDate
                      );
        """)
    boolean findByBirthDateAndFullName(String firstName, String secondName, String patronymic, LocalDate birthDate);

    @Query("""
        select skydiver.id,
               user_info.first_name,
               user_info.second_name,
               user_info.patronymic,
               skydiver.begin_of_sport_career,
               skydiver.sport_rank,
               skydiver.is_internal,
               skydiver.gender
        from skydiver_view as skydiver
        left join user_info on user_info.id = skydiver.id
        limit :limit offset :offset;
        """)
    List<SkydiverShortInfoProjection> getPage(long limit, long offset);

    @Modifying
    @Query("""
        update skydiver
        set is_deleted = :deleted
        where id = :skydiverId
        """)
    void updateByIdSetDeleted(long skydiverId, boolean deleted);

    boolean existsById(long id);

    List<SkydiverShortInfoProjection> filter(Map<String, Object> filters, long limit, long offset);

    Long countFiltered(Map<String, Object> filters);
}
