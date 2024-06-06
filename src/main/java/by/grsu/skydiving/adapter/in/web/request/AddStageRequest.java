package by.grsu.skydiving.adapter.in.web.request;

import java.util.Set;

public record AddStageRequest(
    Set<CollegiumRefereeRequest> mainCollegium,
    Set<CollegiumRefereeRequest> collegium,
    Integer stageNumber
) {
}
