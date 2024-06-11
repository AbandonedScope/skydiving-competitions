package by.grsu.skydiving.application.domain.model.pivot;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
@Builder
public class PivotTeamResult implements Comparable<PivotTeamResult> {
    private Long teamId;
    private String teamName;
    @Singular
    private List<MemberInfo> members;
    private Integer jumpingSum;
    private Float acrobaticsSum;
    private Integer jumpingCompetitionRank;
    private Integer acrobaticsCompetitionRank;


    @Override
    public int compareTo(PivotTeamResult o) {
        return Integer.compare(jumpingSum, o.jumpingSum);
    }
}
