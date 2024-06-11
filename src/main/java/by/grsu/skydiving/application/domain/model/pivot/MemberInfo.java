package by.grsu.skydiving.application.domain.model.pivot;

import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import by.grsu.skydiving.application.domain.model.skydiver.Gender;
import by.grsu.skydiving.application.domain.model.skydiver.SportRank;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class MemberInfo implements Comparable<MemberInfo> {
    private long memberId;
    private long teamId;
    private String teamName;
    private FullName name;
    private Gender gender;
    private boolean isJunior;
    private SportRank sportRank;
    private int memberNumber;
    private boolean participatesInAcrobatics;
    private List<JumpingShortInfo> jumping;
    private Integer jumpingSum;
    private List<AcrobaticsShortInfo> acrobatics;
    private float acrobaticsSum;
    private Integer jumpingCompetitionRank;
    private Integer acrobaticsCompetitionRank;
    private Integer overallCompetitionRank;

    @Override
    public int compareTo(MemberInfo o) {
        return Integer.compare(jumpingSum, o.jumpingSum);
    }
}
