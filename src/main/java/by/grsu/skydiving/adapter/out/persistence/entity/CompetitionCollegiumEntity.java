package by.grsu.skydiving.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("competition_collegium")
public class CompetitionCollegiumEntity {
    @Id
    @Column("id")
    private Long id;
    @Column("competition_id")
    private Long competitionId;
}
