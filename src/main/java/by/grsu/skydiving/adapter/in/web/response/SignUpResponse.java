package by.grsu.skydiving.adapter.in.web.response;

public record SignUpResponse(
        Long id,
        String firstName,
        String secondName,
        String patronymic,
        short role,
        String login,
        String password
) {
}
