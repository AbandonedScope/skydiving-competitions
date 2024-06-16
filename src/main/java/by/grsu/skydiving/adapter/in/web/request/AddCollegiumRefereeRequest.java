package by.grsu.skydiving.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddCollegiumRefereeRequest(
    @NotNull
    Long refereeId,
    @NotNull
    Integer refereeNumber,
    @NotBlank
    String workPerformed,
    @NotNull
    Boolean isMainCollegium
) {
}
