package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.competition.CollegiumReferee;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.domain.model.competition.RefereeGroups;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickRefereeing;
import by.grsu.skydiving.application.domain.model.trickRefereeing.TrickRefereeingFullInfo;
import by.grsu.skydiving.application.port.in.AddTrickRefereeingUseCase;
import by.grsu.skydiving.application.port.in.GetRefereesGroupsByCompetitionStageIdUseCase;
import by.grsu.skydiving.application.port.out.SaveTrickRefereeingPort;
import by.grsu.skydiving.common.UseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@UseCase
@AllArgsConstructor
public class AddTrickRefereeingService implements AddTrickRefereeingUseCase {
    private final GetRefereesGroupsByCompetitionStageIdUseCase refereesUseCase;
    private final SaveTrickRefereeingPort saveTrickRefereeingPort;

    @Override
    public TrickRefereeing addTrickRefereeing(AddTrickRefereeingCommand command) {
        RefereeGroups groups = refereesUseCase.findRefereesByCompetitionStageId(command.competitionId()); // TODO: check it later with new info from customer
        List<Referee> referees = groups.collegium().stream()
                .map(CollegiumReferee::referee)
                .toList();

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
