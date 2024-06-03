package by.grsu.skydiving.adapter.in.web.mapper;

import static org.mapstruct.MappingConstants.ComponentModel;

import by.grsu.skydiving.adapter.in.web.request.SignInRequest;
import by.grsu.skydiving.adapter.in.web.request.SignUpRequest;
import by.grsu.skydiving.adapter.in.web.response.SignInResponse;
import by.grsu.skydiving.adapter.in.web.response.SignUpResponse;
import by.grsu.skydiving.adapter.in.web.response.UserCredentialsResponse;
import by.grsu.skydiving.adapter.in.web.response.UserInfoResponse;
import by.grsu.skydiving.application.domain.model.auth.JwtAuthCredentials;
import by.grsu.skydiving.application.domain.model.auth.JwtToken;
import by.grsu.skydiving.application.domain.model.auth.UserAuthInfo;
import by.grsu.skydiving.application.domain.model.auth.UserCredentials;
import by.grsu.skydiving.application.domain.model.auth.UserRole;
import by.grsu.skydiving.application.domain.model.common.UserInfo;
import by.grsu.skydiving.application.port.in.SignInUseCase.SignInQuery;
import by.grsu.skydiving.application.port.in.SignUpUserUseCase.SignUpUserCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

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

    @Mapping(target = "id", source = "userId")
    @Mapping(target = "firstName", source = "name.firstName")
    @Mapping(target = "secondName", source = "name.secondName")
    @Mapping(target = "patronymic", source = "name.patronymic")
    @Mapping(target = "login", source = "credentials.login")
    @Mapping(target = "password", source = "credentials.password")
    SignUpResponse toResponse(UserAuthInfo info);

    @Mapping(target = "id", source = "userId")
    @Mapping(target = "firstName", source = "name.firstName")
    @Mapping(target = "secondName", source = "name.secondName")
    @Mapping(target = "patronymic", source = "name.patronymic")
    UserInfoResponse toResponse(UserInfo userInfo);

    UserCredentialsResponse toResponse(UserCredentials credentials);

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
