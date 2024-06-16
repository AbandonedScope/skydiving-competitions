package by.grsu.skydiving.application.domain.service;

import static by.grsu.skydiving.application.domain.model.common.FilteringFieldsNames.COMPETITION_ID_NOT_IN_FILTER;

import by.grsu.skydiving.application.domain.model.skydiver.SkydiverShortInfo;
import by.grsu.skydiving.application.port.in.GetListOfSkydiversThatNotParticipateInCompetitionUseCase;
import by.grsu.skydiving.application.port.out.FilterSkydiversShortInfoPort;
import by.grsu.skydiving.common.UseCase;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetListOfSkydiversThatNotParticipateInCompetitionService
    implements GetListOfSkydiversThatNotParticipateInCompetitionUseCase {
    private final FilterSkydiversShortInfoPort filterSkydiversShortInfoPort;

    @Override
    public List<SkydiverShortInfo> getFreeSkydiversForCompetition(Long competitionId) {
        return filterSkydiversShortInfoPort.filter(Map.of(COMPETITION_ID_NOT_IN_FILTER, competitionId));
    }
}
