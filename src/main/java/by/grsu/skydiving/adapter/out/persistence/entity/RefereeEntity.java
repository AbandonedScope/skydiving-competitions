package by.grsu.skydiving.adapter.out.persistence.entity;

import by.grsu.skydiving.application.domain.model.competition.RefereeCategory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("referee")
public class RefereeEntity {
    @Id
    @Column("id")
    private Long id;
    @Column("user_info_id")
    private Integer userInfoId;
    private RefereeCategory category;
}
