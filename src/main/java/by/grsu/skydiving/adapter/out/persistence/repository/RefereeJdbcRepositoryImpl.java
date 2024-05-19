package by.grsu.skydiving.adapter.out.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefereeJdbcRepositoryImpl {
    private final DSLContext create;
    private final JdbcTemplate jdbcTemplate;

//
//    public List<RefereeProjection> filter(Map<String, Object> filters, long limit, long offset) {
//        Query query = create.select(
//                        SKYDIVER_VIEW.ID,
//                        USER_INFO_VIEW.FIRST_NAME,
//                        USER_INFO_VIEW.SECOND_NAME,
//                        USER_INFO_VIEW.PATRONYMIC,
//                        SKYDIVER_VIEW.BEGIN_OF_SPORT_CAREER,
//                        SKYDIVER_VIEW.SPORT_SPECIALIZATION,
//                        SKYDIVER_VIEW.SPORT_DEGREE,
//                        SKYDIVER_VIEW.IS_INTERNAL,
//                        SKYDIVER_VIEW.GENDER)
//                .from(SKYDIVER_VIEW.leftJoin(USER_INFO_VIEW)
//                        .on(SKYDIVER_VIEW.ID.eq(USER_INFO_VIEW.ID))
//                )
//                .where(buildConditions(filters))
//                .limit(limit)
//                .offset(offset);
//
//        String sqlQuery = query.getSQL();
//
//        return jdbcTemplate.query(sqlQuery, rowMapper(), query.getBindValues().toArray());
//    }
//
//    public Long countFiltered(Map<String, Object> filters) {
//        Query query = create.select(count())
//                .from(SKYDIVER_VIEW.leftJoin(USER_INFO_VIEW)
//                        .on(SKYDIVER_VIEW.ID.eq(USER_INFO_VIEW.ID))
//                )
//                .where(buildConditions(filters));
//
//        String sqlQuery = query.getSQL();
//
//        return jdbcTemplate.queryForObject(sqlQuery, Long.class, query.getBindValues().toArray());
//    }
//
//    private List<Condition> buildConditions(Map<String, Object> filters) {
//        return filters.entrySet().stream()
//                .map(this::buildCondition)
//                .toList();
//    }
//
//    private Condition buildCondition(Map.Entry<String, Object> entry) {
//        String key = entry.getKey();
//        Object value = entry.getValue();
//
//        return switch (key) {
//            case "name" -> concat(
//                    concat(USER_INFO_VIEW.SECOND_NAME, space(1)),
//                    concat(USER_INFO_VIEW.FIRST_NAME, space(1)),
//                    concat(USER_INFO_VIEW.PATRONYMIC, space(1))
//            ).like((String) value);
//            case "category" -> SKYDIVER_VIEW.SPORT_DEGREE.eq((Integer) value);
//            case null, default -> noCondition();
//        };
//    }
//
//    private RowMapper<RefereeProjection> rowMapper() {
//
//        return (rs, rowNum) -> {
//            return RefereeProjection.builder()
//                    .id(rs.getLong("id"))
//                    .firstName(rs.getString("first_name"))
//                    .secondName(rs.getString("second_name"))
//                    .patronymic(rs.getString("patronymic"))
//                    .category(rs.getInteger("category"))
//                    .build();
//        };
//    }
}
