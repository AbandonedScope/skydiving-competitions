package by.grsu.skydiving.adapter.out.persistence.entity;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("skydiver_view")
public class SkydiverEntity implements Persistable<Long> {
    @Id
    @Column("id")
    private Long id;
    @Column("gender")
    private Integer gender;
    @Column("birth_date")
    private LocalDate birthDate;
    @Column("place_of_birth")
    private String placeOfBirth;
    @Column("place_of_work")
    private String placeOfWork;
    @Column("education")
    private String education;
    @Column("phone_number")
    private String phoneNumber;
    @Column("couch_name")
    private String couchName;
    @Column("begin_of_sport_career")
    private LocalDate beginDateOfSportCareer;
    @Column("sport_rank")
    private Integer sportRank;
    @Column("is_internal")
    private boolean isInternal = true;

    @Transient
    @Column("jumping_amount")
    private int jumpingAmount;

    @Transient
    private boolean isNew;

    @Override
    public boolean isNew() {
        return isNew;
    }
}
