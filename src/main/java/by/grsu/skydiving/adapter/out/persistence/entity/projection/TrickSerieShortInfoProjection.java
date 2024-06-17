package by.grsu.skydiving.adapter.out.persistence.entity.projection;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
public class TrickSerieShortInfoProjection {
    @Column("id")
    private Long trickSerieId;
    @Column("serie_number")
    private Integer serieNumber;
    @Column("round_number")
    private Integer roundNumber;
    @Column("name")
    private String competitionName;
}
