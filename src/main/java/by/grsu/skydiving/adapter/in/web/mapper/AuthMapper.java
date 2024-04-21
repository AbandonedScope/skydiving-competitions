package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.dto.SignInRequest;
import by.grsu.skydiving.adapter.in.web.dto.SignInResponse;
import by.grsu.skydiving.application.domain.model.auth.JwtAuthCredentials;
import by.grsu.skydiving.application.domain.model.auth.JwtToken;
import by.grsu.skydiving.application.port.in.SignInUseCase.SignInQuery;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
        componentModel = ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface AuthMapper {
    SignInQuery toQuery(SignInRequest request);

    SignInResponse toResponse(JwtAuthCredentials credentials);

    default String toResponse(JwtToken source) {
        return source.getToken();
    }
}
