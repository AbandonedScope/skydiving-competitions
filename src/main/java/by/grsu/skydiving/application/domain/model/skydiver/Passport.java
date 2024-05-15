package by.grsu.skydiving.application.domain.model.skydiver;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record Passport(
        String series,
        String number,
        String personalNumber,
        String issuingAuthority,
        LocalDate issuedDate
) {
}
