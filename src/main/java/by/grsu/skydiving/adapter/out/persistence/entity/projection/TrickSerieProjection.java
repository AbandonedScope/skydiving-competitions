package by.grsu.skydiving.adapter.out.persistence.entity.projection;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
public class TrickSerieProjection {
    private Long id;
    private Long competitionMemberDetailId;
    @Column("referee_id")
    private Long refereeId;
    @Column("referee_number")
    private Long refereeNumber;
    @Column("member_number")
    private Integer skydiverNumber;
    @Column("serie_number")
    private Integer serieNumber;
    @Column("round_number")
    private Integer roundNumber;
    @Column("penalty_reason")
    private Integer penaltyReason;
    private Float timeWithoutPenalty;
    private Boolean isTimeSubmitted;
}
