package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.SignInRequest;
import by.grsu.skydiving.adapter.in.web.response.SignInResponse;
import by.grsu.skydiving.adapter.in.web.dto.SignUpRequest;
import by.grsu.skydiving.adapter.in.web.dto.SignUpResponse;
import by.grsu.skydiving.application.domain.model.UserInfo;
import by.grsu.skydiving.application.domain.model.auth.JwtAuthCredentials;
import by.grsu.skydiving.application.domain.model.auth.JwtToken;
import by.grsu.skydiving.application.domain.model.auth.UserRole;
import by.grsu.skydiving.application.port.in.SignInUseCase.SignInQuery;
import by.grsu.skydiving.application.port.in.SignUpUserUseCase.SignUpUserCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
        componentModel = ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface AuthMapper {
    @Mapping(target = "fullName.firstName", source = "firstName")
    @Mapping(target = "fullName.secondName", source = "secondName")
    @Mapping(target = "fullName.patronymic", source = "patronymic")
    SignUpUserCommand toCommand(SignUpRequest request);

    @Mapping(target = "firstName", source = "name.firstName")
    @Mapping(target = "secondName", source = "name.secondName")
    @Mapping(target = "patronymic", source = "name.patronymic")
    @Mapping(target = "login", source = "credentials.login")
    @Mapping(target = "password", source = "credentials.password")
    SignUpResponse toResponse(UserInfo info);

    SignInQuery toQuery(SignInRequest request);

    SignInResponse toResponse(JwtAuthCredentials credentials);

    default UserRole map(short ordinal) {
        return UserRole.valueOf(ordinal);
    }

    default short map(UserRole role) {
        return (short) role.ordinal();
    }

    default String toResponse(JwtToken source) {
        return source.getToken();
    }
}
