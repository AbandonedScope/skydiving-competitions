package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.RefereeRequest;
import by.grsu.skydiving.adapter.in.web.response.PageResponse;
import by.grsu.skydiving.adapter.in.web.response.RefereeGroupsResponse;
import by.grsu.skydiving.adapter.in.web.response.RefereeResponse;
import by.grsu.skydiving.adapter.in.web.response.RefereeShortInfoResponse;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.competition.CollegiumReferee;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.domain.model.competition.RefereeGroups;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface RefereeMapper {

    @Mapping(target = "name.firstName", source = "firstName")
    @Mapping(target = "name.secondName", source = "secondName")
    @Mapping(target = "name.patronymic", source = "patronymic")
    @Mapping(target = "category", source = "category")
    Referee toDomain(RefereeRequest refereeRequest);

    @Mapping(target = "firstName", source = "referee.name.firstName")
    @Mapping(target = "secondName", source = "referee.name.secondName")
    @Mapping(target = "patronymic", source = "referee.name.patronymic")
    @Mapping(target = "category", source = "referee.category")
    @Mapping(target = "id", source = "referee.id")
    RefereeResponse toResponse(CollegiumReferee referee);

    @Mapping(target = "mainCollegium", source = "mainCollegium")
    @Mapping(target = "collegium", source = "collegium")
    RefereeGroupsResponse toResponse(RefereeGroups groups);

    @Mapping(target = "firstName", source = "name.firstName")
    @Mapping(target = "secondName", source = "name.secondName")
    @Mapping(target = "patronymic", source = "name.patronymic")
    RefereeShortInfoResponse toResponse(Referee shortInfo);

    PageResponse<RefereeShortInfoResponse> toResponse(DomainPage<Referee> domainPage);

    Set<RefereeResponse> toResponses(Set<CollegiumReferee> referees);
}
