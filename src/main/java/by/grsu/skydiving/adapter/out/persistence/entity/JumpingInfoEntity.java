package by.grsu.skydiving.adapter.out.persistence.entity;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("jumping")
public class JumpingInfoEntity {
    @Id
    @Column("id")
    private Long id;
    @Column("competition_member_detail_id")
    private Long competitionMemberDetailsId;
    @Column("skydiver_id")
    private Long skydiverId;
    @Column("referee_id")
    private Long refereeId;
    @Column("attempt_number")
    private Integer number;
    @Column("accuracy")
    private Integer accuracy;
    @Column("performance_date")
    private LocalDate performanceDate;
}
