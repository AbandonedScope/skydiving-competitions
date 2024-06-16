package by.grsu.skydiving.adapter.out.persistence.entity;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table("competition_view")
public class CompetitionEntity {
    @Id
    @Column("id")
    private Long id;
    @Column("name")
    private String name;
    @Column("begin_date")
    private LocalDate beginDate;
    @Column("end_date")
    private LocalDate endDate;
    @Column("address")
    private String address;
    @Column("status")
    private Integer status;
}
