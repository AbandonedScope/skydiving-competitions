package by.grsu.skydiving.application.domain.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessagesConstants {
    public static final String LOGIN_NULL_OR_BLANK_KEY = "blank-login";
    public static final String PASSWORD_NULL_OR_BLANK_KEY = "blank-password";
    public static final String FIRSTNAME_INCORRECT_VALUE_KEY = "incorrect-firstname";
    public static final String SECONDNAME_INCORRECT_VALUE_KEY = "incorrect-secondname";
    public static final String PATRONYMIC_INCORRECT_VALUE_KEY = "incorrect-patronymic";
    public static final String TEAM_NAME_INCORRECT_VALUE_KEY = "incorrect-teamName";
    public static final String TEAM_SKYDIVERS_INCORRECT_VALUE_KEY = "incorrect-teamSkydivers";
    public static final String INDIVIDUAL_COMPETITION_SKYDIVERS_INCORRECT_VALUE_KEY =
        "incorrect-individualCompetitionSkydivers";
    public static final String ADDRESS_INCORRECT_VALUE_KEY = "incorrect_address";
    public static final String JOB_NAME_INCORRECT_VALUE_KEY = "incorrect_jobName";
    public static final String JOB_POSITION_INCORRECT_VALUE_KEY = "incorrect_jobPosition";
    public static final String PHONE_NUMBER_INCORRECT_VALUE_KEY = "incorrect_phoneNumber";


    public static final String LOGIN_IS_NULL_OR_BLANK_MESSAGE =
        "User credential validation. Failed login check. Login is null or blank.";
    public static final String FIRSTNAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE =
        "User fullname validation. Failed firstname check. Firstname is null or blank or incorrect length.";
    public static final String SECONDNAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE =
        "User fullname validation. Failed secondname check. Second is null or blank or incorrect length.";
    public static final String PATRONYMIC_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE =
        "User fullname validation. Failed patronymic check. Patronymic is null or blank or incorrect length.";
    public static final String PASSWORD_IS_NULL_OR_BLANK_MESSAGE =
        "User credential validation. Failed Password check. password is null or blank.";
    public static final String TEAM_NAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE =
        "Team validation. Failed team name check. TeamName is null or blank or incorrect length.";
    public static final String TEAM_SKYDIVERS_IS_NULL_MESSAGE =
        "Team validation. Failed team page check. Team page is null or blank.";
    public static final String ADDRESS_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE =
        "Address validation. Failed address check. Address is null or blank or incorrect length.";
    public static final String JOB_NAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE =
        "Parent job validation. Job name name check. Job name is null or blank or incorrect length.";
    public static final String JOB_POSITION_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE =
        "Parent job validation. Failed job position check. Job position is null or blank or incorrect length.";
    public static final String PHONE_NUMBER_DO_NOT_MATCH_PATTERN_OR_NULL_MESSAGE =
        "Validation. Failed phone number check. Phone number is null or doesn't match the pattern.";
    public static final String INDIVIDUAL_COMPETITION_SKYDIVERS_NULL_MESSAGE =
        "Individual competition page validation. Failed individual competition page check. Skydivers is null";
}
