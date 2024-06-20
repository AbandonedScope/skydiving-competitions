package by.grsu.skydiving.adapter.in.web.request;

import by.grsu.skydiving.application.domain.model.competition.RefereeCategory;

public record RefereeRequest(
    String firstName,
    String secondName,
    String patronymic,
    RefereeCategory category
) {
}
