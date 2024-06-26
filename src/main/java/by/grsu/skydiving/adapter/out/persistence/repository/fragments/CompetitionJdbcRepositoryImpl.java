package by.grsu.skydiving.adapter.out.persistence.repository.fragments;

import static generated.Tables.COMPETITION;
import static generated.Tables.COMPETITION_VIEW;
import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.noCondition;

import by.grsu.skydiving.adapter.out.persistence.entity.CompetitionEntity;
import by.grsu.skydiving.application.domain.model.competition.CompetitionStatus;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CompetitionJdbcRepositoryImpl {
    private final DSLContext create;
    private final JdbcTemplate jdbcTemplate;

    public List<CompetitionEntity> filter(Map<String, Object> filters, int limit, long offset) {
        Query query = create.select(
                COMPETITION_VIEW.ID,
                COMPETITION_VIEW.NAME,
                COMPETITION_VIEW.BEGIN_DATE,
                COMPETITION_VIEW.END_DATE,
                COMPETITION_VIEW.STATUS,
                COMPETITION_VIEW.ADDRESS)
            .from(COMPETITION_VIEW)
            .where(buildConditions(filters))
            .orderBy(COMPETITION_VIEW.ID.desc())
            .limit(limit)
            .offset(offset);

        String sqlQuery = query.getSQL();

        return jdbcTemplate.query(sqlQuery, rowMapper(), query.getBindValues().toArray());
    }

    public Long countFiltered(Map<String, Object> filters) {
        Query query = create.select(count())
            .from(COMPETITION_VIEW)
            .where(buildConditions(filters));

        String sqlQuery = query.getSQL();

        return jdbcTemplate.queryForObject(sqlQuery, Long.class, query.getBindValues().toArray());
    }

    public List<Long> updateCompetitionStatusesToRunning() {
        LocalDateTime now = LocalDateTime.now();

        return create.update(COMPETITION)
            .set(COMPETITION.STATUS, CompetitionStatus.RUNNING.getId())
            .where(COMPETITION.END_DATE.greaterOrEqual(now))
            .and(COMPETITION.BEGIN_DATE.lessOrEqual(now))
            .and(COMPETITION.STATUS.eq(
                    CompetitionStatus.CREATED.getId()
                )
            )
            .returning(COMPETITION.ID)
            .fetch(COMPETITION.ID);
    }

    public List<Long> updateCompetitionStatusesToCompleted() {
        LocalDateTime now = LocalDateTime.now();

        return create.update(COMPETITION)
            .set(COMPETITION.STATUS, CompetitionStatus.COMPLETED.getId())
            .where(COMPETITION.END_DATE.lessOrEqual(now))
            .and(COMPETITION.STATUS.eq(
                    CompetitionStatus.RUNNING.getId()
                )
            )
            .returning(COMPETITION.ID)
            .fetch(COMPETITION.ID);
    }

    public List<Long> updateCompetitionStatusesToCanceled() {
        LocalDateTime now = LocalDateTime.now();

        return create.update(COMPETITION)
            .set(COMPETITION.STATUS, CompetitionStatus.CANCELED.getId())
            .where(COMPETITION.END_DATE.lessOrEqual(now))
            .and(COMPETITION.BEGIN_DATE.lessOrEqual(now))
            .and(COMPETITION.STATUS.eq(
                    CompetitionStatus.INITIAL.getId()
                )
            )
            .returning(COMPETITION.ID)
            .fetch(COMPETITION.ID);
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
            case "isCompleted" -> buildIsCompleted((Boolean) value);
            case null, default -> noCondition();
        };
    }

    private Condition buildIsCompleted(boolean isCompleted) {
        if (isCompleted) {
            return COMPETITION_VIEW.STATUS.in(
                CompetitionStatus.COMPLETED.getId(),
                CompetitionStatus.CANCELED.getId()
            );
        }

        return COMPETITION_VIEW.STATUS.in(
            CompetitionStatus.INITIAL.getId(),
            CompetitionStatus.CREATED.getId(),
            CompetitionStatus.RUNNING.getId()
        );
    }

    private RowMapper<CompetitionEntity> rowMapper() {

        return (rs, rowNum) -> {
            Date beginDate = rs.getDate("begin_date");
            LocalDate beginDateOfCompetition = beginDate == null
                ? null
                : beginDate.toLocalDate();
            Date endDate = rs.getDate("end_date");
            LocalDate endDateOfCompetition = endDate == null
                ? null
                : endDate.toLocalDate();

            return CompetitionEntity.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .address(rs.getString("address"))
                .beginDate(beginDateOfCompetition)
                .endDate(endDateOfCompetition)
                .status(rs.getInt("status"))
                .build();
        };
    }
}
