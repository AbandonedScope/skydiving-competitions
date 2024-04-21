package by.grsu.skydiving.application.domain.model.auth;

import by.grsu.skydiving.application.domain.exception.InvalidRoleNumberException;

import java.util.Arrays;

public enum UserRole {
    ADMIN,
    REFEREE,
    SKYDIVER,
    SECRETARY;

    public static UserRole valueOf(int roleNumber) {
        return Arrays.stream(UserRole.values())
                .filter(role -> role.ordinal() == roleNumber)
                .findFirst()
                .orElseThrow(() -> new InvalidRoleNumberException(roleNumber));
    }
}
