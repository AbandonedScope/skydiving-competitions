package by.grsu.skydiving.adapter.out.persistence.repository.fragments;

import static by.grsu.skydiving.application.domain.model.common.FilteringFieldsNames.CATEGORY_FILTER;
import static by.grsu.skydiving.application.domain.model.common.FilteringFieldsNames.NAME_FILTER;
import static generated.Tables.REFEREE_VIEW;
import static generated.Tables.USER_INFO_VIEW;
import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.noCondition;

import by.grsu.skydiving.adapter.out.persistence.entity.projection.RefereeProjection;
import by.grsu.skydiving.application.domain.model.competition.RefereeCategory;
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
public class RefereeJdbcRepositoryImpl {
    private final DSLContext create;
    private final JdbcTemplate jdbcTemplate;

    public List<RefereeProjection> filter(Map<String, Object> filters, long limit, long offset) {
        Query query = create.select(
                REFEREE_VIEW.ID,
                USER_INFO_VIEW.FIRST_NAME,
                USER_INFO_VIEW.SECOND_NAME,
                USER_INFO_VIEW.PATRONYMIC,
                REFEREE_VIEW.CATEGORY)
            .from(REFEREE_VIEW.leftJoin(USER_INFO_VIEW)
                .on(REFEREE_VIEW.ID.eq(USER_INFO_VIEW.ID))
            )
            .where(buildConditions(filters))
            .limit(limit)
            .offset(offset);

        String sqlQuery = query.getSQL();

        return jdbcTemplate.query(sqlQuery, rowMapper(), query.getBindValues().toArray());
    }

    public Long countFiltered(Map<String, Object> filters) {
        Query query = create.select(count())
            .from(REFEREE_VIEW.leftJoin(USER_INFO_VIEW)
                .on(REFEREE_VIEW.ID.eq(USER_INFO_VIEW.ID))
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
            case NAME_FILTER -> buildNameFullTextSearchCondition((String) value);
            case CATEGORY_FILTER -> REFEREE_VIEW.CATEGORY.eq(((RefereeCategory) value).ordinal());
            case null, default -> noCondition();
        };
    }

    private Condition buildNameFullTextSearchCondition(String name) {
        return DSL.condition("to_tsvector(" +
                             USER_INFO_VIEW.SECOND_NAME.getName() + " || ' ' || "
                             + USER_INFO_VIEW.FIRST_NAME.getName() + " || ' ' || "
                             + USER_INFO_VIEW.PATRONYMIC.getName() + ") " +
                             "@@ " +
                             "to_tsquery(regexp_replace(cast(plainto_tsquery('russian', '" + name + "') as text)," +
                             "E'(\\'\\\\w+\\')', E'\\\\1:*', 'g'))");
    }

    private RowMapper<RefereeProjection> rowMapper() {

        return (rs, rowNum) -> RefereeProjection.builder()
            .id(rs.getLong("id"))
            .firstName(rs.getString("first_name"))
            .secondName(rs.getString("second_name"))
            .patronymic(rs.getString("patronymic"))
            .category(rs.getInt("category"))
            .build();
    }
}
