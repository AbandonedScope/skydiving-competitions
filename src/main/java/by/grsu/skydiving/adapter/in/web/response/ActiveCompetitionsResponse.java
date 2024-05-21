package by.grsu.skydiving.adapter.in.web.response;

import java.util.List;
import lombok.Builder;

@Builder
public record ActiveCompetitionsResponse(
    List<CompetitionResponse> competitions
) {
}
