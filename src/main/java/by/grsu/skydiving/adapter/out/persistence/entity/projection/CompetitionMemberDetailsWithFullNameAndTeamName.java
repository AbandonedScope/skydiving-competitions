package by.grsu.skydiving.adapter.out.persistence.entity.projection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompetitionMemberDetailsWithFullNameAndTeamName {
    private Long id;
    private Boolean isJunior;
    private Long teamId;
    private String teamName;
    private Long skydiverId;
    private String firstName;
    private String secondName;
    private String patronymic;
    private Long competitionId;
    private Integer memberNumber;
    private Boolean isIndividual;

    public void setIsJunior(boolean isJunior) {
        this.isJunior = isJunior;
    }

    public void setIsIndividual(boolean isIndividual) {
        this.isIndividual = isIndividual;
    }
}
