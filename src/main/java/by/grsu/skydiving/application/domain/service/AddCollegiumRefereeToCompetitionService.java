package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.CollegiumReferee;
import by.grsu.skydiving.application.domain.model.competition.Competition;
import by.grsu.skydiving.application.domain.model.competition.CompetitionCollegium;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.port.in.AddCollegiumRefereeToCompetitionUseCase;
import by.grsu.skydiving.application.port.in.GetCompetitionUseCase;
import by.grsu.skydiving.application.port.in.GetRefereeByIdUseCase;
import by.grsu.skydiving.application.port.out.SaveCompetitionPort;
import by.grsu.skydiving.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddCollegiumRefereeToCompetitionService implements AddCollegiumRefereeToCompetitionUseCase {
    private final GetCompetitionUseCase getCompetitionUseCase;
    private final GetRefereeByIdUseCase getRefereeByIdUseCase;
    private final SaveCompetitionPort saveCompetitionPort;

    @Override
    public CompetitionCollegium addCollegiumReferee(AddCollegiumRefereeCommand command) {
        long competitionId = command.competitionId();
        long refereeId = command.refereeId();
        Competition competition = getCompetitionUseCase.getCompetition(competitionId);
        Referee referee = getRefereeByIdUseCase.getRefereeById(refereeId);

        CollegiumReferee collegiumReferee = CollegiumReferee.builder()
            .refereeNumber(command.refereeNumber())
            .referee(referee)
            .workPerformed(command.workPerformed())
            .build();

        competition.addRefereeToCollegium(collegiumReferee, command.isMainCollegium());

        competition = saveCompetitionPort.save(competition);

        return competition.getCollegium();
    }
}
