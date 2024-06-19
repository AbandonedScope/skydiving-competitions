package by.grsu.skydiving.application.domain.model.pivot;

import java.util.List;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
@Builder
public class PivotTeamResult {
    private Long teamId;
    private String teamName;
    @Singular
    private List<MemberInfo> members;
    private Float acrobaticsSum;
    private Integer jumpingCompetitionRank;
    private Integer acrobaticsCompetitionRank;

    public Integer getJumpingSum() {
        List<Integer> sums = members.stream()
            .map(MemberInfo::getJumpingSum)
            .filter(Objects::nonNull)
            .toList();

        Integer teamJumpingSum = null;
        if (!sums.isEmpty()) {
            teamJumpingSum = sums.stream()
                .reduce(0, Integer::sum);
        }


        return teamJumpingSum;
    }

    public Float getAcrobaticsSum() {
        List<Float> sums = members.stream()
            .map(MemberInfo::getAcrobaticsSum)
            .filter(Objects::nonNull)
            .toList();

        Float teamAcrobaticsSum = null;
        if (!sums.isEmpty()) {
            teamAcrobaticsSum = sums.stream()
                .reduce(0.0f, Float::sum);
        }


        return teamAcrobaticsSum;
    }

    public Integer getOverallCompetitionRank() {
        if (jumpingCompetitionRank == null || acrobaticsCompetitionRank == null) {
            return null;
        }

        return Integer.sum(jumpingCompetitionRank, acrobaticsCompetitionRank);
    }
}
