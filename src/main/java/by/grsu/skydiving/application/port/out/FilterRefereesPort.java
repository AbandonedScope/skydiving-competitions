package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.competition.Referee;

import java.util.Map;

public interface FilterRefereesPort {
    DomainPage<Referee> filter(Map<String, Object> filters, long pageNumber, int pageSize);
}
