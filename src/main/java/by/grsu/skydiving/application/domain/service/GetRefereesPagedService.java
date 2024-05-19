package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.common.FilterQuery;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.port.in.GetFilteredRefereesUseCase;
import by.grsu.skydiving.application.port.out.FilterRefereesPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
@UseCase
@RequiredArgsConstructor
public class GetRefereesPagedService implements GetFilteredRefereesUseCase {
    private final FilterRefereesPort filterRefereesPort;

    @Override
    public List<Referee> getFiltered(FilterQuery query) {
        return List.of();
    }
}
