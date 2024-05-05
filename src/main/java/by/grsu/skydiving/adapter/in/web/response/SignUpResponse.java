package by.grsu.skydiving.adapter.in.web.response;

public record SignUpResponse(
        String firstName,
        String secondName,
        String patronymic,
        short role,
        String login,
        String password
) {
}
