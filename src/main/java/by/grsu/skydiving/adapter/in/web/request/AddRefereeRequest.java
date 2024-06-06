package by.grsu.skydiving.adapter.in.web.request;

public record AddRefereeRequest(
    String firstName,
    String secondName,
    String patronymic,
    String category
) {
}
