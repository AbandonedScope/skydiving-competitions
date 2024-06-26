package by.grsu.skydiving.adapter.out.persistence.entity.projection;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

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
    @Column("penalty_reason")
    private Integer penaltyReason;
    private String name;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String address;
}
