package by.grsu.skydiving.application.domain.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessagesConstants {
    public static final String LOGIN_NULL_OR_BLANK_KEY = "blank-login";
    public static final String PASSWORD_NULL_OR_BLANK_KEY = "blank-password";
    public static final String LOGIN_IS_NULL_OR_BLANK_MESSAGE = "User credential validation. Failed login check. Login is null or blank.";
    public static final String PASSWORD_IS_NULL_OR_BLANK_MESSAGE = "User credential validation. Failed Password check. password is null or blank.";

}
