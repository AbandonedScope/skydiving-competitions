package by.grsu.skydiving.application.domain.model.trick;

import by.grsu.skydiving.application.domain.model.competition.Referee;
import java.util.List;
import lombok.Builder;

@Builder
public record TrickRefereeingFullInfo(
    List<Referee> referees,
    Integer roundNumber,
    Integer serieNumber,
    Long skydiverId,
    Long competitionId
) {
}
