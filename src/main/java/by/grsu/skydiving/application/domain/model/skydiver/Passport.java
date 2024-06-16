package by.grsu.skydiving.application.domain.model.skydiver;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record Passport(
    Long id,
    String series,
    String number,
    String personalNumber,
    String issuingAuthority,
    LocalDate issuedDate
) {
}
