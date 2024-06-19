package by.grsu.skydiving.application.domain.model.pivot;

import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import java.util.List;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class MemberInfo {
    private long memberId;
    private long teamId;
    private String teamName;
    private FullName name;
    private boolean isJunior;
    private int memberNumber;
    private List<JumpingShortInfo> jumping;
    private List<AcrobaticsShortInfo> acrobatics;
    private Integer jumpingCompetitionRank;
    private Integer acrobaticsCompetitionRank;

    public Integer getJumpingSum() {
        Integer jumpingSum = null;

        if (jumping != null && !jumping.isEmpty()) {
            jumpingSum = jumping.stream()
                .map(JumpingShortInfo::accuracy)
                .reduce(0, Integer::sum);
        }

        return jumpingSum;
    }

    public Float getAcrobaticsSum() {
        Float acrobaticsSum = null;

        if (acrobatics != null && !acrobatics.isEmpty()) {
            acrobaticsSum = acrobatics.stream()
                .map(AcrobaticsShortInfo::time)
                .filter(Objects::nonNull)
                .reduce(0.0f, Float::sum);
        }

        return acrobaticsSum;
    }

    public Integer getOverallCompetitionRank() {
        if (jumpingCompetitionRank == null || acrobaticsCompetitionRank == null) {
            return null;
        }

        return Integer.sum(jumpingCompetitionRank, acrobaticsCompetitionRank);
    }

    public boolean getParticipatesInAcrobatics() {
        return getAcrobaticsSum() != null;
    }
}
