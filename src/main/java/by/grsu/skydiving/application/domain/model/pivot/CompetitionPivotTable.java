package by.grsu.skydiving.application.domain.model.pivot;

import java.util.List;
import lombok.Builder;
import lombok.Singular;

@Builder
public record CompetitionPivotTable(
    Long competitionId,
    String competitionName,
    @Singular
    List<PivotTeamResult> teams,
    @Singular
    List<MemberInfo> individuals
) {
}
