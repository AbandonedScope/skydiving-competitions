package by.grsu.skydiving.adapter.out.persistence;

import static generated.tables.Competition.COMPETITION;

import by.grsu.skydiving.application.port.out.GetNextMemberNumberAndIncrementPort;
import by.grsu.skydiving.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

@PersistenceAdapter
@RequiredArgsConstructor
public class CompetitionMemberNumberAdapter implements GetNextMemberNumberAndIncrementPort {
    private final DSLContext dslContext;

    @Override
    public int getAndIncrement(Long competitionId) {
        int incremented = dslContext.update(COMPETITION)
            .set(COMPETITION.NEXT_MEMBER_NUMBER, DSL.select(COMPETITION.NEXT_MEMBER_NUMBER.plus(1))
                .from(COMPETITION)
                .where(COMPETITION.ID.eq(competitionId))
                .forUpdate())
            .where(COMPETITION.ID.eq(competitionId))
            .returningResult(COMPETITION.NEXT_MEMBER_NUMBER)
            .fetchOne()
            .get(COMPETITION.NEXT_MEMBER_NUMBER);

        return incremented - 1;
    }
}
