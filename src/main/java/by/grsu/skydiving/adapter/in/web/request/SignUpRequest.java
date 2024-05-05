package by.grsu.skydiving.adapter.in.web.request;

public record SignUpRequest(
        String firstName,
        String secondName,
        String patronymic,
        short role
) {
}
