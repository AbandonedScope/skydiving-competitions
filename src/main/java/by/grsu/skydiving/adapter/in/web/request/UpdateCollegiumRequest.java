package by.grsu.skydiving.adapter.in.web.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record UpdateCollegiumRequest(
    @NotNull
    Long collegiumId,
    @Valid
    @NotEmpty
    Set<AddCollegiumRefereeRequest> mainCollegium,
    @Valid
    @NotEmpty
    Set<AddCollegiumRefereeRequest> collegium
) {
}
