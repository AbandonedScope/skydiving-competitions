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
@Table("competition_member_detail")
public class CompetitionMemberDetailsEntity {
    @Id
    @Column("id")
    private Long id;
    @Column("is_junior")
    private Boolean isJunior;
    @Column("team_id")
    private Long teamId;
    @Column("skydiver_id")
    private Long skydiverId;
    @Column("competition_id")
    private Long competitionId;
    @Column("member_number")
    private Integer memberNumber;
}
