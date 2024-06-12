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
    private Integer serieNumber;
    private Integer roundNumber;
    private Float timeWithoutPenalty;
    private Boolean isTimeSubmitted;
}
