package by.grsu.skydiving.application.port.in;

import by.grsu.skydiving.application.domain.model.common.FilterQuery;
import by.grsu.skydiving.application.domain.model.competition.Referee;

import java.util.List;

public interface GetFilteredRefereesUseCase {
    List<Referee> getFiltered(FilterQuery query);
}
