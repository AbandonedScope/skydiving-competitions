package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.competition.CompetitionMember;
import by.grsu.skydiving.application.domain.model.competition.CompetitionStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.Builder;

@Builder
public record CompetitionResponse(
    Long id,
    String name,
    List<TeamWithMembersResponse> teams,
    Set<CompetitionMember> individuals,
    LocalDate beginDate,
    LocalDate endDate,
    String place,
    List<CompetitionStageResponse> stages,
    Integer numberOfStages,
    CompetitionStatus status
) {
}
