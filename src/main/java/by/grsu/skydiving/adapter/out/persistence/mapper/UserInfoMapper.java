package by.grsu.skydiving.adapter.out.persistence.mapper;

import by.grsu.skydiving.adapter.out.persistence.entity.UserInfoEntity;
import by.grsu.skydiving.adapter.out.persistence.entity.projection.UserInfoWithoutCredentials;
import by.grsu.skydiving.application.domain.model.auth.UserAuthInfo;
import by.grsu.skydiving.application.domain.model.auth.UserInfoForToken;
import by.grsu.skydiving.application.domain.model.auth.UserRole;
import by.grsu.skydiving.application.domain.model.common.UserInfo;
import by.grsu.skydiving.application.domain.model.competition.Referee;
import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface UserInfoMapper {
    @Mapping(target = "firstName", source = "name.firstName")
    @Mapping(target = "secondName", source = "name.secondName")
    @Mapping(target = "patronymic", source = "name.patronymic")
    @Mapping(target = "login", source = "credentials.login")
    @Mapping(target = "password", source = "credentials.password")
    UserInfoEntity toEntity(UserAuthInfo info);

    @Mapping(target = "firstName", source = "name.firstName")
    @Mapping(target = "secondName", source = "name.secondName")
    @Mapping(target = "patronymic", source = "name.patronymic")
    @Mapping(target = "role", constant = "2")
    UserInfoEntity toEntity(Skydiver info);

    @Mapping(target = "firstName", source = "info.name.firstName")
    @Mapping(target = "secondName", source = "info.name.secondName")
    @Mapping(target = "patronymic", source = "info.name.patronymic")
    @Mapping(target = "role", constant = "0")
    UserInfoEntity toEntity(Referee info);

    @Mapping(target = "name.firstName", source = "firstName")
    @Mapping(target = "name.secondName", source = "secondName")
    @Mapping(target = "name.patronymic", source = "patronymic")
    @Mapping(target = "credentials.login", source = "login")
    @Mapping(target = "credentials.password", source = "password")
    UserAuthInfo toDomain(UserInfoEntity entity);

    @Mapping(target = "name.firstName", source = "firstName")
    @Mapping(target = "name.secondName", source = "secondName")
    @Mapping(target = "name.patronymic", source = "patronymic")
    UserInfo toDomain(UserInfoWithoutCredentials entity);

    UserInfoForToken toTokenInfo(UserInfoEntity entity);

    default short map(UserRole role) {
        return (short) role.ordinal();
    }

    default UserRole map(short role) {
        return UserRole.valueOf(role);
    }
}
