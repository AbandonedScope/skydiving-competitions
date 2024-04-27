package by.grsu.skydiving.application.domain.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessagesConstants {
    public static final String LOGIN_NULL_OR_BLANK_KEY = "blank-login";
    public static final String PASSWORD_NULL_OR_BLANK_KEY = "blank-password";
    public static final String SHOE_SIZE_INCORRECT_VALUE_KEY = "incorrect-shoesize";
    public static final String JACKET_SIZE_INCORRECT_VALUE_KEY = "incorrect-jacketsize";
    public static final String PANTS_SIZE_INCORRECT_VALUE_KEY = "incorrect-pantssize";
    public static final String FIRSTNAME_INCORRECT_VALUE_KEY = "incorrect-firstname";
    public static final String SECONDNAME_INCORRECT_VALUE_KEY = "incorrect-secondname";
    public static final String PATRONYMIC_INCORRECT_VALUE_KEY = "incorrect-patronymic";
    public static final String PLACE_INCORRECT_VALUE_KEY = "incorrect-place";
    public static final String TEAM_NAME_INCORRECT_VALUE_KEY = "incorrect-teamName";

    public static final String LOGIN_IS_NULL_OR_BLANK_MESSAGE = "User credential validation. Failed login check. Login is null or blank.";
    public static final String FIRSTNAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE = "User fullname validation. Failed firstname check. Firstname is null or blank or incorrect length.";
    public static final String SECONDNAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE = "User fullname validation. Failed secondname check. Second is null or blank or incorrect length.";
    public static final String PATRONYMIC_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE = "User fullname validation. Failed patronymic check. Patronymic is null or blank or incorrect length.";
    public static final String PASSWORD_IS_NULL_OR_BLANK_MESSAGE = "User credential validation. Failed Password check. password is null or blank.";
    public static final String SHOE_SIZE_INCORRECT_VALUE_MESSAGE = "Clothing size validation. Incorrect shoesize";
    public static final String JACKET_SIZE_INCORRECT_VALUE_MESSAGE = "Clothing size validation. Incorrect jacketsize";
    public static final String PANTS_SIZE_INCORRECT_VALUE_MESSAGE = "Clothing size validation. Incorrect pantssize";
    public static final String PLACE_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE = "User validation. Failed place check. Place is null or blank or incorrect length.";
    public static final String TEAM_NAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE = "Team validation. Failed team name check. TeamName is null or blank or incorrect length.";
}
