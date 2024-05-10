package by.grsu.skydiving.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Setter
@Table("competition")
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
    @Column("number_of_stages")
    private Integer numberOfStages;
}