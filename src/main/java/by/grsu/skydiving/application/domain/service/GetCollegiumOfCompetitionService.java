package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.CompetitionCollegium;
import by.grsu.skydiving.application.port.in.GetCollegiumOfCompetitionUseCase;
import by.grsu.skydiving.application.port.out.FindCollegiumOfCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetCollegiumOfCompetitionService implements GetCollegiumOfCompetitionUseCase {
    private final FindCollegiumOfCompetitionPort findCollegiumPort;

    @Override
    public CompetitionCollegium getByCompetitionId(Long competitionId) {
        return findCollegiumPort.findByCompetitionId(competitionId)
            .orElse(null);
    }
}
