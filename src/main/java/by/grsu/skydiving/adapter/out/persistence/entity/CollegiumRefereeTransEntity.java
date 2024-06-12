package by.grsu.skydiving.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("competition_collegium_referee_trans")
public class CollegiumRefereeTransEntity {
    @Id
    @Column("id")
    private Long id;
    @Column("competition_collegium_id")
    private Long competitionCollegiumId;
    @Column("referee_id")
    private Integer refereeId;
    @Column("referee_number")
    private Integer refereeNumber;
    @Column("work_performed")
    private String workPerformed;
    @Column("is_main_collegium")
    private boolean isMainCollegium;

    public void setIsMainCollegium(boolean isMainCollegium) {
        this.isMainCollegium = isMainCollegium;
    }
}
