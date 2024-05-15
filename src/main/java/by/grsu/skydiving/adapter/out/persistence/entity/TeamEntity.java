package by.grsu.skydiving.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("team_view")
public class TeamEntity {
    @Id
    @Column("id")
    Long id;
    @Column("name")
    String name;
}
