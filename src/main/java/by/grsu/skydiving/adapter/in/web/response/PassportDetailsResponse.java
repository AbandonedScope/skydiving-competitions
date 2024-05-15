package by.grsu.skydiving.adapter.in.web.response;

import java.time.LocalDate;

public record PassportDetailsResponse(
        String series,
        String number,
        String personalNumber,
        String issuingAuthority,
        LocalDate issuedDate
) {
}
