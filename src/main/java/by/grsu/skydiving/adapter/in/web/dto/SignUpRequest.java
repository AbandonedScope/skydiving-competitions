package by.grsu.skydiving.adapter.in.web.dto;

public record SignUpRequest(
        String firstName,
        String secondName,
        String patronymic,
        short role
) {
}
