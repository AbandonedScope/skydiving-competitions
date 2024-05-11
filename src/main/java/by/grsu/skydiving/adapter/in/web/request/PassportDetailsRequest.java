package by.grsu.skydiving.adapter.in.web.request;

import java.time.LocalDate;

public record PassportDetailsRequest(
    String series,
    String number,
    String personalNumber,
    String issuingAuthority,
    LocalDate issuedDate
) {
}
