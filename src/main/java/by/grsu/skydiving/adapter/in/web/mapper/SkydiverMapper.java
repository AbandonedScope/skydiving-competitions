package by.grsu.skydiving.adapter.in.web.mapper;

import by.grsu.skydiving.adapter.in.web.request.PassportDetailsRequest;
import by.grsu.skydiving.adapter.in.web.request.SkydiverRequest;
import by.grsu.skydiving.adapter.in.web.response.*;
import by.grsu.skydiving.application.domain.model.common.DomainPage;
import by.grsu.skydiving.application.domain.model.skydiver.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface SkydiverMapper {
    @Mapping(target = "name.firstName", source = "firstName")
    @Mapping(target = "name.secondName", source = "secondName")
    @Mapping(target = "name.patronymic", source = "patronymic")
    @Mapping(target = "passport", source = "passportDetails")
    @Mapping(target = "phoneNumber", source = "phone")
    Skydiver toDomain(SkydiverRequest request);

    Passport toDomain(PassportDetailsRequest request);

    @Mapping(target = "height", source = "height.height")
    @Mapping(target = "weight", source = "weight.weight")
    SkydiverResponse toResponse(Skydiver skydiver);

    @Mapping(target = "sportCareer", source = "sportCareer.sportDegree")
    SkydiverShortInfoResponse toResponse(SkydiverShortInfo shortInfo);

    PageResponse<SkydiverShortInfoResponse> toResponse(DomainPage<SkydiverShortInfo> domainPage);

    PassportDetailsResponse toResponse(Passport passport);

    SportCareerResponse toResponse(SportCareer sportCareer);

    FullNameResponse toResponse(FullName name);

    ClothingSizeResponse toResponse(ClothingSize clothingSize);

    default PhoneNumber mapPhoneNumber(String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }

    default String mapPhoneNumber(PhoneNumber phoneNumber) {
        return phoneNumber.number();
    }

    default Address mapAddress(String address) {
        return new Address(address);
    }

    default String mapAddress(Address address) {
        return address.address();
    }
}
