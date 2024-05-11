package by.grsu.skydiving.application.domain.model.skydiver;

public record SkydiverShortInfo(
        Long id,
        FullName name,
        SportCareer sportCareer,
        Gender gender,
        boolean isInternal
) {
}
