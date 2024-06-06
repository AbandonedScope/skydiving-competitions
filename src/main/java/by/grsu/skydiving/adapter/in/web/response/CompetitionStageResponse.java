package by.grsu.skydiving.adapter.in.web.response;

import java.util.Set;
import lombok.Builder;

@Builder
public record CompetitionStageResponse(
    Long id,
    Integer number,
    Set<RefereeResponse> mainCollegium,
    Set<RefereeResponse> collegium
) {
}
