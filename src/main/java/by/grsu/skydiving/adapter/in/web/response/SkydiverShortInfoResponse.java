package by.grsu.skydiving.adapter.in.web.response;

public record SkydiverShortInfoResponse(
        Long id,
        FullNameResponse name,
        SportCareerResponse career
) {
}
