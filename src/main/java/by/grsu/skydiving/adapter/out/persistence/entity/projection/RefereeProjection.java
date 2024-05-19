package by.grsu.skydiving.adapter.out.persistence.entity.projection;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RefereeProjection {
    private Long id;
    private short category;
    private String firstName;
    private String secondName;
    private String patronymic;
}
