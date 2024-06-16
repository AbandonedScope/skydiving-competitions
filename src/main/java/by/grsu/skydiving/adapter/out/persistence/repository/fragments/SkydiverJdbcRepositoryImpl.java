package by.grsu.skydiving.adapter.out.persistence.repository.fragments;

import static by.grsu.skydiving.application.domain.model.common.FilteringFieldsNames.COMPETITION_ID_NOT_IN_FILTER;
import static by.grsu.skydiving.application.domain.model.common.FilteringFieldsNames.GENDER_FILTER;
import static by.grsu.skydiving.application.domain.model.common.FilteringFieldsNames.IS_INTERNAL_FILTER;
import static by.grsu.skydiving.application.domain.model.common.FilteringFieldsNames.NAME_FILTER;
import static by.grsu.skydiving.application.domain.model.common.FilteringFieldsNames.SPORT_RANK_FILTER;
import static generated.Tables.COMPETITION_MEMBER_DETAIL;
import static generated.Tables.SKYDIVER_VIEW;
import static generated.Tables.USER_INFO_VIEW;
import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.noCondition;

import by.grsu.skydiving.adapter.out.persistence.entity.projection.SkydiverShortInfoProjection;
import by.grsu.skydiving.application.domain.model.skydiver.SportRank;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.impl.DSL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SkydiverJdbcRepositoryImpl {
    private final DSLContext create;
    private final JdbcTemplate jdbcTemplate;

    public List<SkydiverShortInfoProjection> filter(Map<String, Object> filters) {
        Query query = create.select(
                SKYDIVER_VIEW.ID,
                USER_INFO_VIEW.FIRST_NAME,
                USER_INFO_VIEW.SECOND_NAME,
                USER_INFO_VIEW.PATRONYMIC,
                SKYDIVER_VIEW.BEGIN_OF_SPORT_CAREER,
                SKYDIVER_VIEW.SPORT_RANK,
                SKYDIVER_VIEW.IS_INTERNAL,
                SKYDIVER_VIEW.GENDER)
            .from(SKYDIVER_VIEW.leftJoin(USER_INFO_VIEW)
                .on(SKYDIVER_VIEW.ID.eq(USER_INFO_VIEW.ID))
            )
            .where(buildConditions(filters))
            .orderBy(
                USER_INFO_VIEW.SECOND_NAME,
                USER_INFO_VIEW.FIRST_NAME,
                USER_INFO_VIEW.PATRONYMIC,
                USER_INFO_VIEW.ID
            );

        String sqlQuery = query.getSQL();

        return jdbcTemplate.query(sqlQuery, rowMapper(), query.getBindValues().toArray());
    }

    public List<SkydiverShortInfoProjection> filter(Map<String, Object> filters, long limit, long offset) {
        Query query = create.select(
                SKYDIVER_VIEW.ID,
                USER_INFO_VIEW.FIRST_NAME,
                USER_INFO_VIEW.SECOND_NAME,
                USER_INFO_VIEW.PATRONYMIC,
                SKYDIVER_VIEW.BEGIN_OF_SPORT_CAREER,
                SKYDIVER_VIEW.SPORT_RANK,
                SKYDIVER_VIEW.IS_INTERNAL,
                SKYDIVER_VIEW.GENDER)
            .from(SKYDIVER_VIEW.leftJoin(USER_INFO_VIEW)
                .on(SKYDIVER_VIEW.ID.eq(USER_INFO_VIEW.ID))
            )
            .where(buildConditions(filters))
            .orderBy(
                USER_INFO_VIEW.SECOND_NAME,
                USER_INFO_VIEW.FIRST_NAME,
                USER_INFO_VIEW.PATRONYMIC,
                USER_INFO_VIEW.ID
            )
            .limit(limit)
            .offset(offset);

        String sqlQuery = query.getSQL();

        return jdbcTemplate.query(sqlQuery, rowMapper(), query.getBindValues().toArray());
    }

    public Long countFiltered(Map<String, Object> filters) {
        Query query = create.select(count())
            .from(SKYDIVER_VIEW.leftJoin(USER_INFO_VIEW)
                .on(SKYDIVER_VIEW.ID.eq(USER_INFO_VIEW.ID))
            )
            .where(buildConditions(filters));

        String sqlQuery = query.getSQL();

        return jdbcTemplate.queryForObject(sqlQuery, Long.class, query.getBindValues().toArray());
    }

    private List<Condition> buildConditions(Map<String, Object> filters) {
        return filters.entrySet().stream()
            .map(this::buildCondition)
            .toList();
    }

    private Condition buildCondition(Map.Entry<String, Object> entry) {
        String key = entry.getKey();
        Object value = entry.getValue();

        return switch (key) {
            case GENDER_FILTER -> SKYDIVER_VIEW.GENDER.eq((Integer) value);
            case NAME_FILTER -> buildNameFullTextSearchCondition((String) value);
            case SPORT_RANK_FILTER -> SKYDIVER_VIEW.SPORT_RANK.eq((short) ((SportRank) value).getId());
            case IS_INTERNAL_FILTER -> SKYDIVER_VIEW.IS_INTERNAL.eq((Boolean) value);
            case COMPETITION_ID_NOT_IN_FILTER ->
                SKYDIVER_VIEW.ID.notIn(DSL.select(COMPETITION_MEMBER_DETAIL.SKYDIVER_ID)
                    .from(COMPETITION_MEMBER_DETAIL)
                    .where(COMPETITION_MEMBER_DETAIL.COMPETITION_ID.eq((Long) value))
                );
            case null, default -> noCondition();
        };
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
                .sportRank(rs.getObject("sport_rank", Integer.class))
                .gender(rs.getObject("gender", Integer.class))
                .isInternal(rs.getBoolean("is_internal"))
                .build();
        };
    }

    Condition buildNameFullTextSearchCondition(String name) {
        return DSL.condition("to_tsvector(" +
                             USER_INFO_VIEW.SECOND_NAME.getName() + " || ' ' || "
                             + USER_INFO_VIEW.FIRST_NAME.getName() + " || ' ' || "
                             + USER_INFO_VIEW.PATRONYMIC.getName() + ") " +
                             "@@ " +
                             "to_tsquery(regexp_replace(cast(plainto_tsquery('russian', '" + name + "') as text)," +
                             "E'(\\'\\\\w+\\')', E'\\\\1:*', 'g'))");
    }
}
