package by.grsu.skydiving.application.domain.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessagesConstants {
    public static final String LOGIN_NULL_OR_BLANK_KEY = "blank-login";
    public static final String PASSWORD_NULL_OR_BLANK_KEY = "blank-password";
    public static final String SHOE_SIZE_INCORRECT_VALUE_KEY = "incorrect-shoeSize";
    public static final String JACKET_SIZE_INCORRECT_VALUE_KEY = "incorrect-jacketSize";
    public static final String PANTS_SIZE_INCORRECT_VALUE_KEY = "incorrect-pantsSize";
    public static final String FIRSTNAME_INCORRECT_VALUE_KEY = "incorrect-firstname";
    public static final String SECONDNAME_INCORRECT_VALUE_KEY = "incorrect-secondname";
    public static final String PATRONYMIC_INCORRECT_VALUE_KEY = "incorrect-patronymic";
    public static final String PLACE_INCORRECT_VALUE_KEY = "incorrect-place";
    public static final String TEAM_NAME_INCORRECT_VALUE_KEY = "incorrect-teamName";
    public static final String TEAM_SKYDIVERS_INCORRECT_VALUE_KEY = "incorrect-teamSkydivers";
    public static final String INDIVIDUAL_COMPETITION_SKYDIVERS_INCORRECT_VALUE_KEY = "incorrect-individualCompetitionSkydivers";
    public static final String WEIGHT_INCORRECT_VALUE_KEY = "incorrect-weight";
    public static final String HEIGHT_INCORRECT_VALUE_KEY = "incorrect-height";
    public static final String BEGIN_DATE_OF_SPORT_CAREER_INCORRECT_VALUE_KEY = "incorrect-beginDateOfSportCareer";
    public static final String SPORT_SPECIALIZATION_INCORRECT_VALUE_KEY = "incorrect_sportSpecialization";
    public static final String SPORT_DEGREE_INCORRECT_VALUE_KEY = "incorrect_sportDegree";
    public static final String ADDRESS_INCORRECT_VALUE_KEY = "incorrect_address";
    public static final String JOB_NAME_INCORRECT_VALUE_KEY = "incorrect_jobName";
    public static final String JOB_POSITION_INCORRECT_VALUE_KEY = "incorrect_jobPosition";
    public static final String PHONE_NUMBER_INCORRECT_VALUE_KEY = "incorrect_phoneNumber";
    public static final String FLIGHT_HEIGHT_INCORRECT_VALUE_KEY = "incorrect_flightHeight";
    public static final String SPEED_INCORRECT_VALUE_KEY = "incorrect_speed";
    public static final String TIME_DELAY_OF_PARACHUT_OPENING_INCORRECT_VALUE_KEY = "incorrect_timeDelayOfParachutOpening";
    public static final String AVIATION_UNIT_INCORRECT_VALUE_KEY = "incorrect_aviationUnit";
    public static final String JUMP_NUMBER_BILLABLE_INCORRECT_VALUE_KEY = "incorrect_jumpNumberBillable";
    public static final String NEXT_JUMP_NUMBER_DURING_YEAR_INCORRECT_VALUE_KEY = "incorrect_nextJumpNumberDuringYear";
    public static final String NEXT_JUMP_NUMBER_IN_COMPETITION_UNIT_INCORRECT_VALUE_KEY = "incorrect_nextJumpNumberInCompetition";
    public static final String COMPETITION_STAGE_NUMBER_INCORRECT_VALUE_KEY = "incorrect_stageNumber";
    public static final String COMPETITION_STAGE_MAIN_JUDGE_COLLEGIUM_VALUE_KEY = "incorrect_mainJudgeCollegium";
    public static final String COMPETITION_STAGE_JUDGE_COLLEGIUM_VALUE_KEY = "incorrect_judgeCollegium";


    public static final String LOGIN_IS_NULL_OR_BLANK_MESSAGE = "User credential validation. Failed login check. Login is null or blank.";
    public static final String FIRSTNAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE = "User fullname validation. Failed firstname check. Firstname is null or blank or incorrect length.";
    public static final String SECONDNAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE = "User fullname validation. Failed secondname check. Second is null or blank or incorrect length.";
    public static final String PATRONYMIC_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE = "User fullname validation. Failed patronymic check. Patronymic is null or blank or incorrect length.";
    public static final String PASSWORD_IS_NULL_OR_BLANK_MESSAGE = "User credential validation. Failed Password check. password is null or blank.";
    public static final String SHOE_SIZE_INCORRECT_VALUE_MESSAGE = "Clothing size validation. Incorrect shoesize.";
    public static final String JACKET_SIZE_INCORRECT_VALUE_MESSAGE = "Clothing size validation. Incorrect jacketsize.";
    public static final String PANTS_SIZE_INCORRECT_VALUE_MESSAGE = "Clothing size validation. Incorrect pantssize.";
    public static final String PLACE_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE = "User validation. Failed place check. Place is null or blank or incorrect length.";
    public static final String TEAM_NAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE = "Team validation. Failed team name check. TeamName is null or blank or incorrect length.";
    public static final String TEAM_SKYDIVERS_IS_NULL_MESSAGE = "Team validation. Failed team skydivers check. Team skydivers is null or blank.";
    public static final String WEIGHT_OUT_OF_RANGE_OR_NULL_MESSAGE = "Skydiver validation. Failed weight check. Weight is null or out of range.";
    public static final String HEIGHT_OUT_OF_RANGE_OR_NULL_MESSAGE = "Skydiver validation. Failed height check. Height is null or out of range.";
    public static final String BEGIN_DATE_OF_SPORT_IS_NULL_OR_INCORRECT_DATE_MESSAGE = "Skydiver validation. Failed begin date of sport career check. Begin date of sport is null or incorrect date.";
    public static final String SPORT_SPECIALIZATION_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE = "Skydiver validation. Failed sport specialization check. Sport specialization is null or blank or incorrect length.";
    public static final String SPORT_DEGREE_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE = "Skydiver validation. Failed sport degree check. Sport degree is null or blank or incorrect length.";
    public static final String ADDRESS_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE = "Address validation. Failed address check. Address is null or blank or incorrect length.";
    public static final String JOB_NAME_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE = "Parent job validation. Job name name check. Job name is null or blank or incorrect length.";
    public static final String JOB_POSITION_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE = "Parent job validation. Failed job position check. Job position is null or blank or incorrect length.";
    public static final String PHONE_NUMBER_DO_NOT_MATCH_PATTERN_OR_NULL_MESSAGE = "Validation. Failed phone number check. Phone number is null or doesn't match the pattern.";
    public static final String FLIGHT_HEIGHT_NULL_OR_NEGATIVE_MESSAGE = "Jumping validation. Failed flight height check. Flight height is null or negative";
    public static final String SPEED_NULL_OR_NEGATIVE_MESSAGE = "Jumping validation. Failed speed check. Speed is null or negative";
    public static final String TIME_DELAY_OF_PARACHUT_OPENING_NULL_OR_NEGATIVE_MESSAGE = "Jumping validation. Failed timeDelayOfParachutOpening check. TimeDelayOfParachutOpening is null or negative";
    public static final String AVIATION_UNIT_IS_NULL_OR_BLANK_OR_INCORRECT_LENGTH_MESSAGE = "Jumping validation. Failed aviation unit check. AviationUnit is null or blank or incorrect length.";
    public static final String JUMP_NUMBER_BILLABLE_IS_NULL_OR_NEGATIVE_MESSAGE = "Jumping validation. Failed jump number billable check. Jump number billable is null or negative";
    public static final String NEXT_JUMP_NUMBER_DURING_YEAR_IS_NULL_OR_NEGATIVE_MESSAGE = "Jumping validation. Failed next jump number during year check. Next jump number during year is null or negative";
    public static final String NEXT_JUMP_NUMBER_IN_COMPETITION_IS_NULL_OR_NEGATIVE_MESSAGE = "Jumping validation. Failed next jump number in competition check. Next jump number in competition height is null or negative";
    public static final String COMPETITION_STAGE_NUMBER_NULL_OR_INVALID_MESSAGE = "Competition stage validation. Failed number of stage check. Number of stage is null or invalid";
    public static final String COMPETITION_STAGE_MAIN_JUDGE_COLLEGIUM_NULL_MESSAGE = "Competition stage validation. Failed main judge collegium of stage check. Collegium is null";
    public static final String COMPETITION_STAGE_JUDGE_COLLEGIUM_NULL_MESSAGE = "Competition stage validation. Failed judge collegium of stage check. Collegium is null";
    public static final String INDIVIDUAL_COMPETITION_SKYDIVERS_NULL_MESSAGE = "Individual competition skydivers validation. Failed individual competition skydivers check. Skydivers is null";
}
