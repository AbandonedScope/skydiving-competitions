package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.AddTrickRefereeingRequest;
import by.grsu.skydiving.adapter.in.web.request.UpdateTrickSerieRequest;
import by.grsu.skydiving.adapter.in.web.response.RefereeingResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickAttemptsFullInfoResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickSerieExtendedResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickSerieOfSkydiverResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickSerieShortInfoResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickSerieTimeResponse;
import by.grsu.skydiving.adapter.in.web.response.TrickSerieWithCompetitionShortInfoResponse;
import by.grsu.skydiving.adapter.in.web.response.UpdatedTrickSerieResponse;
import by.grsu.skydiving.application.domain.model.competition.CompetitionStatus;
import by.grsu.skydiving.application.domain.model.trick.PenaltyReason;
import by.grsu.skydiving.application.domain.model.trick.Refereeing;
import by.grsu.skydiving.application.domain.model.trick.RefereeingResult;
import by.grsu.skydiving.application.domain.model.trick.TrickSerie;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieInfoForUpdate;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieOfSkydiver;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieShortInfo;
import by.grsu.skydiving.application.domain.model.trick.TrickSerieTime;
import by.grsu.skydiving.application.port.in.UpdateTrickSerieUseCase;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface TrickSerieResponseMapper {
    TrickAttemptMapper trickAttemptsMapper = Mappers.getMapper(TrickAttemptMapper.class);

    @Mapping(target = "trickAttemptsWithScore", source = "trickAttempts")
    @Mapping(target = "refereeId", source = "domain.refereeId")
    @Mapping(target = "refereeNumber", source = "domain.refereeNumber")
    @Mapping(target = "timeWithoutPenalty", source = "domain.timeWithoutPenalty")
    @Mapping(target = "totalPenalty", source = "domain.totalPenalty")
    @Mapping(target = "isTimeSubmitted", source = "domain.isTimeSubmitted")
    @Mapping(target = "penaltyReason", source = "domain.penaltyReason")
    TrickSerieExtendedResponse toTrickSerieExtendedResponse(RefereeingResult domain,
                                                            TrickAttemptsFullInfoResponse trickAttempts);

    @Mapping(target = "skydiverId", source = "request.skydiverId")
    @Mapping(target = "competitionId", source = "request.competitionId")
    @Mapping(target = "id", source = "domain.id")
    @Mapping(target = "refereeId", source = "domain.refereeId")
    @Mapping(target = "refereeNumber", source = "domain.refereeNumber")
    @Mapping(target = "serieNumber", source = "domain.serieNumber")
    @Mapping(target = "roundNumber", source = "domain.roundNumber")
    @Mapping(target = "score", source = "domain.score")
    @Mapping(target = "timeWithoutPenalty", source = "domain.timeWithoutPenalty")
    @Mapping(target = "totalPenalty", source = "domain.totalPenalty")
    @Mapping(target = "isTimeSubmitted", source = "domain.isTimeSubmitted")
    TrickSerieShortInfoResponse toResponse(TrickSerie domain, AddTrickRefereeingRequest request);

    @Mapping(target = "trickSerieId", source = "trickSerieId")
    @Mapping(target = "roundNumber", source = "roundNumber")
    @Mapping(target = "serieNumber", source = "serieNumber")
    @Mapping(target = "competition.competitionId", source = "competition.competitionId")
    @Mapping(target = "competition.name", source = "competition.name")
    @Mapping(target = "competition.beginDate", source = "competition.beginDate")
    @Mapping(target = "competition.endDate", source = "competition.endDate")
    @Mapping(target = "competition.address", source = "competition.address")
    RefereeingResponse toResponse(Refereeing refereeing);
    List<RefereeingResponse> toResponses(List<Refereeing> refereeings);

    TrickSerieWithCompetitionShortInfoResponse toResponse(TrickSerieShortInfo trickSerie);

    UpdateTrickSerieUseCase.UpdateTrickSerieCommand toCommand(UpdateTrickSerieRequest request);

    UpdatedTrickSerieResponse toResponse(TrickSerieInfoForUpdate domain);

    TrickSerieTimeResponse toResponse(TrickSerieTime trickSerie);

    default CompetitionStatus map(int number) {
        return CompetitionStatus.of(number);
    }

    default int map(CompetitionStatus status) {
        return status.getId();
    }

    default PenaltyReason mapToPenaltyReason(int number) {
        return PenaltyReason.of(number);
    }

    default int map(PenaltyReason status) {
        return status.ordinal();
    }

    List<TrickSerieOfSkydiverResponse> mapToResponses(List<TrickSerieOfSkydiver> trickSerieOfSkydivers);

    @Mapping(target = "isCompleted", source = "completed")
    TrickSerieOfSkydiverResponse mapToResponse(TrickSerieOfSkydiver trickSerieOfSkydiver);
}
