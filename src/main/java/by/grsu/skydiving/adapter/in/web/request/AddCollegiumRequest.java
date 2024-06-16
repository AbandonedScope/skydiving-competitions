package by.grsu.skydiving.adapter.in.web.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

public record AddCollegiumRequest(
    @Valid
    @NotEmpty
    Set<AddCollegiumRefereeRequest> mainCollegium,
    @Valid
    @NotEmpty
    Set<AddCollegiumRefereeRequest> collegium
) {
}
