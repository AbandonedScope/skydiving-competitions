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
@Table("trick_attempt")
public class TrickAttemptEntity {
    @Id
    @Column("id")
    Long id;
    @Column("trick_serie_id")
    Long trickSerieId;
    @Column("trick_type")
    Integer trickType;
    @Column("arrow_penalty")
    Integer arrowPenalty;
    @Column("d_penalty")
    Integer dPenalty;
    @Column("s_penalty")
    Integer sPenalty;
    @Column("minus_penalty")
    Integer minusPenalty;
    @Column("plus_minus_penalty")
    Integer plusMinusPenalty;
}
