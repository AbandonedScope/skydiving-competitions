package by.grsu.skydiving.adapter.out.persistence.repository;

import by.grsu.skydiving.adapter.out.persistence.entity.projection.SkydiverShortInfoProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class SkydiverJdbcRepositoryImpl {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String BASE_FILTERING_SQL_QUERY = """
            select skydiver.id,
                   user_info.first_name,
                   user_info.second_name,
                   user_info.patronymic,
                   skydiver.begin_of_sport_career,
                   skydiver.sport_specialization,
                   skydiver.sport_degree,
                   skydiver.is_internal,
                   skydiver.gender
            from skydiver_view as skydiver
            left join user_info on user_info.id = skydiver.id
            """;
    private final String BASE_COUNT_FILTERING_SQL_QUERY = """
            select count(*)
            from skydiver_view as skydiver
            left join user_info on user_info.id = skydiver.id
            """;
    private final String FULL_NAME_LIKE_SQL_PART = " concat_ws(' ', user_info.first_name, user_info.second_name, user_info.patronymic) like :name ";
    private final String GENDER_EQUALS_SQL_PART = " gender = :gender ";
    private final String SPORT_DEGREE_EQUALS_SQL_PART = " sport_degree = :sportDegree ";
    private final String IS_INTERNAL_EQUALS_SQL_PART = " is_internal = :isInternal ";
    private final String LIMIT_OFFSET_SQL_PART = " limit :limit offset :offset;";

    public List<SkydiverShortInfoProjection> filter(Map<String, Object> filters, long limit, long offset) {
        String query = buildQuery(BASE_FILTERING_SQL_QUERY, filters);

        filters.put("limit", limit);
        filters.put("offset", offset);
        SqlParameterSource parameterSource = new MapSqlParameterSource(filters);

        return jdbcTemplate.query(query, parameterSource, rowMapper());
    }

    public Long countFiltered(Map<String, Object> filters, long limit, long offset) {
        String query = buildQuery(BASE_COUNT_FILTERING_SQL_QUERY, filters);

        filters.put("limit", limit);
        filters.put("offset", offset);
        SqlParameterSource parameterSource = new MapSqlParameterSource(filters);

        return jdbcTemplate.queryForObject(query, parameterSource, Long.class);
    }

    private String buildQuery(String query, Map<String, Object> filters) {
        boolean isFirstFilter = false;
        for (String key : filters.keySet()) {
            String filter = switch (key) {
                case "gender" -> GENDER_EQUALS_SQL_PART;
                case "name" -> FULL_NAME_LIKE_SQL_PART;
                case "sportDegree" -> SPORT_DEGREE_EQUALS_SQL_PART;
                case "isInternal" -> IS_INTERNAL_EQUALS_SQL_PART;
                case null, default -> "";
            };

            if (isFirstFilter) {
                filter = " and ".concat(filter);
            } else {
                isFirstFilter = true;
                filter = " where ".concat(filter);
            }

            query = query.concat(filter);
        }

        return query.concat(LIMIT_OFFSET_SQL_PART);
    }

    private RowMapper<SkydiverShortInfoProjection> rowMapper() {

        return (rs, rowNum) -> {
            Date date = rs.getDate("begin_of_sport_career");
            LocalDate beginDateOfSportCareer = date == null
                    ? null
                    : date.toLocalDate();

            return SkydiverShortInfoProjection.builder()
                    .id(rs.getLong("id"))
                    .firstName(rs.getString("first_name"))
                    .secondName(rs.getString("second_name"))
                    .patronymic(rs.getString("patronymic"))
                    .beginDateOfSportCareer(beginDateOfSportCareer)
                    .sportSpecialization(rs.getString("sport_specialization"))
                    .sportDegree(rs.getInt("sport_degree"))
                    .gender(rs.getInt("gender"))
                    .isInternal(rs.getBoolean("is_internal"))
                    .build();
        };
    }
}
