package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.application.domain.model.pivot.AcrobaticsShortInfo;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.jooq.Record;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Result;

public interface AcrobaticsShortInfoRecordMapper {
    Stream<AcrobaticsShortInfo> toDomain(
        Record2<Long, Result<Record3<Integer, BigDecimal, Result<Record>>>> jooqRecord);
}
