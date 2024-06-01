package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import by.grsu.skydiving.application.domain.model.competition.MembersOfCompetition;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import by.grsu.skydiving.application.port.in.AddIndividualToCompetitionUseCase;
import by.grsu.skydiving.application.port.in.GetSkydiverUseCase;
import by.grsu.skydiving.application.port.in.GetUpdatableCompetitionUseCase;
import by.grsu.skydiving.application.port.out.SaveCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddIndividualToCompetitionService implements AddIndividualToCompetitionUseCase {
    private final GetUpdatableCompetitionUseCase getCompetitionUseCase;
    private final SaveCompetitionPort saveCompetitionPort;
    private final GetSkydiverUseCase getSkydiverUseCase;

    @Override
    public MembersOfCompetition addIndividualToCompetition(long competitionId, CompetitionMember individual) {
        Competition competition = getCompetitionUseCase.getCompetitionThatCanBeUpdated(competitionId);

        SkydiverShortInfo skydiver = getSkydiverUseCase.getById(individual.skydiverId());

        competition.addIndividual(skydiver, individual.memberNumber());

        saveCompetitionPort.save(competition);
        return competition.getMembers();
    }
}
