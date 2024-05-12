package by.grsu.skydiving.adapter.out.persistence.entity.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CollegiumRefereeProjection {
    private Long id;
    private String category;
    private String workPerformed;
    private boolean isMainCollegium;
    private Long competitionStageId;
    private String firstName;
    private String secondName;
    private String patronymic;

    public void setIsMainCollegium(boolean isMainCollegium) {
        this.isMainCollegium = isMainCollegium;
    }

    public boolean getIsMainCollegium() {
        return isMainCollegium;
    }
}
