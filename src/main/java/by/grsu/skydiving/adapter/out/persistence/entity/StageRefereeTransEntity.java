package by.grsu.skydiving.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("competition_stage_referee_trans")
public class StageRefereeTransEntity {
    @Id
    @Column("id")
    private Long id;
    @Column("competition_stage_id")
    private Long competitionStageId;
    @Column("referee_id")
    private Integer refereeId;
    @Column("work_performed")
    private String workPerformed;
    @Column("is_main_collegium")
    private boolean isMainCollegium;


    public void setIsMainCollegium(boolean isMainCollegium) {
        this.isMainCollegium = isMainCollegium;
    }
}
