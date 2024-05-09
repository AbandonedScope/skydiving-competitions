package by.grsu.skydiving.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Setter
@Table("skydiver")
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
    @Column("height")
    private Float height;
    @Column("weight")
    private Float weight;
    @Column("shoe_size")
    private Integer shoeSize;
    @Column("jacket_size")
    private Integer jacketSize;
    @Column("pants_size")
    private Integer pantsSize;
    @Column("begin_of_sport_career")
    private LocalDate beginDateOfSportCareer;
    @Column("sport_specialization")
    private String sportSpecialization;
    @Column("sport_degree")
    private Integer sportDegree;

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
