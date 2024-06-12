package by.grsu.skydiving.adapter.in.web.response;

import java.util.Set;
import lombok.Builder;

@Builder
public record CompetitionCollegiumResponse(
    Long id,
    Set<RefereeResponse> mainCollegium,
    Set<RefereeResponse> collegium
) {
}
