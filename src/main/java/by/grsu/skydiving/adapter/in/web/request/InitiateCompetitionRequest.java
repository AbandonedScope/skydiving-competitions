package by.grsu.skydiving.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record InitiateCompetitionRequest(
    @NotBlank
    String name,
    @NotNull
    LocalDate beginDate,
    @NotNull
    LocalDate endDate,
    @NotBlank
    String place
) {
}
