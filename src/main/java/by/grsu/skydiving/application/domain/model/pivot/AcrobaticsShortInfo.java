package by.grsu.skydiving.application.domain.model.pivot;

import lombok.Builder;

@Builder
public record AcrobaticsShortInfo(
    Long competitionMemberDetailsId,
    int number,
    Float time
) {
}
