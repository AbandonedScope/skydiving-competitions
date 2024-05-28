package by.grsu.skydiving.application.domain.model.trickRefereeing;

import by.grsu.skydiving.application.domain.model.competition.Referee;
import lombok.Builder;

import java.util.List;

@Builder
public record TrickRefereeingFullInfo(
        List<Referee> referees,
        Integer roundNumber,
        Integer serieNumber,
        Long skydiverId,
        Long competitionId
) {
}
