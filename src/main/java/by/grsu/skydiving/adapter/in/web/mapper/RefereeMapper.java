package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.AddRefereeRequest;
import by.grsu.skydiving.adapter.in.web.response.RefereeGroupsResponse;
import by.grsu.skydiving.adapter.in.web.response.RefereeResponse;
import by.grsu.skydiving.application.domain.model.competition.CollegiumReferee;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.domain.model.competition.RefereeCategory;
import by.grsu.skydiving.application.domain.model.competition.RefereeGroups;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface RefereeMapper {
    @Mapping(target = "firstName", source = "referee.info.name.firstName")
    @Mapping(target = "secondName", source = "referee.info.name.secondName")
    @Mapping(target = "patronymic", source = "referee.info.name.patronymic")
    @Mapping(target = "category", source = "referee.category")
    @Mapping(target = "id", source = "referee.id")
    RefereeResponse toResponse(CollegiumReferee referee);

    @Mapping(target = "info.name.firstName", source = "firstName")
    @Mapping(target = "info.name.secondName", source = "secondName")
    @Mapping(target = "info.name.patronymic", source = "patronymic")
    @Mapping(target = "category", source = "category")
    Referee toDomain(AddRefereeRequest refereeRequest);

    @Mapping(target = "id", source = "id")
    RefereeResponse toResponse(Referee referee);

    Set<RefereeResponse> toResponses(Set<CollegiumReferee> referees);

    default RefereeGroupsResponse toResponse(RefereeGroups groups) {
        var mainCollegium = toResponses(groups.mainCollegium());
        var collegium = toResponses(groups.collegium());

        return new RefereeGroupsResponse(mainCollegium, collegium);
    }

    default short map(RefereeCategory category) {
        return (short) category.ordinal();
    }

    default RefereeCategory map(short role) {
        return RefereeCategory.valueOf(role);
    }
}
