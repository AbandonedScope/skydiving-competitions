package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.trick.TrickSerieOfSkydiver;
import by.grsu.skydiving.application.port.in.GetTrickSeriesByCompetitionIdUseCase;
import by.grsu.skydiving.application.port.out.GetTrickSeriesByCompetitionIdPort;
import by.grsu.skydiving.common.UseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetTrickSeriesByCompetitionIdService implements GetTrickSeriesByCompetitionIdUseCase {
    private final GetTrickSeriesByCompetitionIdPort trickSeriesByCompetitionIdPort;

    @Override
    public List<TrickSerieOfSkydiver> getByCompetitionId(Long competitionId) {
        return trickSeriesByCompetitionIdPort.getByCompetitionId(competitionId);
    }
}
