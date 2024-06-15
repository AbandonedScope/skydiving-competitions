package by.grsu.skydiving.common.knowledge;

import lombok.Builder;

@Builder
public record EnumModel(
    int id,
    String name,
    String description
) {
}
