package by.grsu.skydiving.adapter.out.persistence.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table("trick_serie")
public class TrickSerieEntity {
    @Id
    @Column("id")
    private Long id;
    @Column("competition_member_detail_id")
    private Long competitionMemberDetailId;
    @Column("referee_id")
    private Long refereeId;
    @Column("score")
    private Integer score;
    @Column("serie_number")
    private Integer serieNumber;
    @Column("round_number")
    private Integer roundNumber;
    @Column("time_without_penalty")
    private Float timeWithoutPenalty;
    @Column("is_time_submitted")
    private Boolean isTimeSubmitted;
}
