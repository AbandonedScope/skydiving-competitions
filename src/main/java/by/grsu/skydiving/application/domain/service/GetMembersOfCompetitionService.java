package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.MembersOfCompetition;
import by.grsu.skydiving.application.port.in.GetMembersOfCompetitionUseCase;
import by.grsu.skydiving.application.port.out.GetMembersOfCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetMembersOfCompetitionService implements GetMembersOfCompetitionUseCase {
    private final GetMembersOfCompetitionPort getMembersOfCompetitionPort;

    @Override
    public MembersOfCompetition getMembersOfCompetition(Long competitionId) {
        return getMembersOfCompetitionPort.getByCompetitionId(competitionId);
    }
}
