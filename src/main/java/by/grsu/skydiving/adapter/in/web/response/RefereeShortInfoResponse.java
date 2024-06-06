package by.grsu.skydiving.adapter.in.web.response;

import by.grsu.skydiving.application.domain.model.competition.RefereeCategory;

public record RefereeShortInfoResponse(
    Long id,
    String firstName,
    String secondName,
    String patronymic,
    RefereeCategory category) {

}
