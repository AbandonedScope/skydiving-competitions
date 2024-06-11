package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.CompetitionCollegium;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickRefereeing;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickRefereeingFullInfo;
import by.grsu.skydiving.application.port.in.AddTrickRefereeingUseCase;
import by.grsu.skydiving.application.port.in.GetCollegiumOfCompetitionUseCase;
import by.grsu.skydiving.application.port.out.SaveTrickRefereeingPort;
import by.grsu.skydiving.common.UseCase;
import java.util.List;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class AddTrickRefereeingService implements AddTrickRefereeingUseCase {
    private final GetCollegiumOfCompetitionUseCase refereesUseCase;
    private final SaveTrickRefereeingPort saveTrickRefereeingPort;

    @Override
    public TrickRefereeing addTrickRefereeing(AddTrickRefereeingCommand command) {
        CompetitionCollegium collegium = refereesUseCase.getByCompetitionId(
            command.competitionId());
        // TODO: check it later with new info from customer
        List<Referee> referees = collegium.getReferees();

        TrickRefereeingFullInfo fullInfo = TrickRefereeingFullInfo.builder()
            .referees(referees)
            .serieNumber(command.serieNumber())
            .roundNumber(command.roundNumber())
            .skydiverId(command.skydiverId())
            .competitionId(command.competitionId())
            .build();

        return saveTrickRefereeingPort.saveAll(fullInfo);
    }
}
