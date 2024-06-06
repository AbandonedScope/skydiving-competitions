package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.competition.RefereeCategory;
import lombok.Builder;

@Builder
public record RefereeResponse(
    Long id,
    String firstName,
    String secondName,
    String patronymic,
    RefereeCategory category,
    String workPerformed
) {
}
