package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import by.grsu.skydiving.application.domain.model.competition.MembersOfCompetition;
import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import by.grsu.skydiving.application.port.in.AddIndividualsToCompetitionUseCase;
import by.grsu.skydiving.application.port.in.GetSkydiverUseCase;
import by.grsu.skydiving.application.port.in.GetUpdatableCompetitionUseCase;
import by.grsu.skydiving.application.port.out.SaveCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddIndividualsToCompetitionService implements AddIndividualsToCompetitionUseCase {
    private final GetUpdatableCompetitionUseCase getCompetitionUseCase;
    private final SaveCompetitionPort saveCompetitionPort;
    private final GetSkydiverUseCase getSkydiverUseCase;

    @Override
    public MembersOfCompetition addIndividualsToCompetition(long competitionId, Set<CompetitionMember> individuals) {
        Competition competition = getCompetitionUseCase.getCompetitionThatCanBeUpdated(competitionId);

        for (CompetitionMember member : individuals) {
            SkydiverShortInfo skydiver = getSkydiverUseCase.getByIdShort(member.skydiverId());
            competition.addIndividual(skydiver);
        }

        saveCompetitionPort.save(competition);
        return competition.getMembers();
    }
}
