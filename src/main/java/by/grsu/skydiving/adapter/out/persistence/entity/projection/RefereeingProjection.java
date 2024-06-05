package by.grsu.skydiving.adapter.out.persistence.entity.projection;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RefereeingProjection {
    @Column("trickSerieId")
    private Long trickSerieId;
    private Integer roundNumber;
    private Integer serieNumber;
    @Column("competitionId")
    private Long competitionId;
    private String name;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String address;
    private Integer numberOfStages;
}
