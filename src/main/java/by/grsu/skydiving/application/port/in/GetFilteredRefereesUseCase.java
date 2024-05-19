package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.common.GetPageQuery;
import by.grsu.skydiving.application.domain.model.competition.Referee;

public interface GetFilteredRefereesUseCase {
    DomainPage<Referee> getPage(GetPageQuery query);
}
