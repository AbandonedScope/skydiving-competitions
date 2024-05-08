package by.grsu.skydiving.adapter.in.web.response;

public record RefereeResponse(
        Long id,
        String firstName,
        String secondName,
        String patronymic,
        String category,
        String workPerformed
) {
}
