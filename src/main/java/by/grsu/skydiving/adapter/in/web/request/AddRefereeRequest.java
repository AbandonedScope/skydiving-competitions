package by.grsu.skydiving.adapter.in.web.request;

import by.grsu.skydiving.application.domain.model.competition.RefereeCategory;

public record AddRefereeRequest(
    String firstName,
    String secondName,
    String patronymic,
    RefereeCategory category
) {
}
