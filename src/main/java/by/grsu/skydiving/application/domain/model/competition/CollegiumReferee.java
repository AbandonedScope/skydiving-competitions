package by.grsu.skydiving.application.domain.model.competition;

import lombok.Builder;

@Builder
public record CollegiumReferee(
    Referee referee,
    String workPerformed
) {
}
