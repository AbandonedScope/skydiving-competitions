package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.exception.business.CompetitionNotFoundException;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.port.in.GetCompetitionUseCase;
import by.grsu.skydiving.application.port.out.FindCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetCompetitionService implements GetCompetitionUseCase {
    private final FindCompetitionPort findPort;

    @Override
    public Competition getCompetition(Long competitionId) {
        return findPort.findFullById(competitionId)
                .orElseThrow(() -> new CompetitionNotFoundException(competitionId));
    }
}
