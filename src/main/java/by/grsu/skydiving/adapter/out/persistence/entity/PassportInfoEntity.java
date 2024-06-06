package by.grsu.skydiving.adapter.out.persistence.entity;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("passport_info")
public class PassportInfoEntity {
    @Id
    @Column("id")
    private Long id;
    @Column("skydiver_id")
    private Long skydiverId;
    @Column("series")
    private String series;
    @Column("number")
    private String number;
    @Column("personal_number")
    private String personalNumber;
    @Column("issuing_authority")
    private String issuingAuthority;
    @Column("issued_date")
    private LocalDate issuedDate;
}
