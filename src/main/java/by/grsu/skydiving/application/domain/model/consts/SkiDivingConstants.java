package by.grsu.skydiving.application.domain.model.consts;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class SkiDivingConstants {
    public static final Integer  MIN_AGE = 10;
    public static final Integer  MAX_AGE = 100;

    public static final Integer  MIN_WEIGHT = 35;
    public static final Integer  MAX_WEIGHT = 180;

    public static final Integer  MIN_HEIGHT = 100;
    public static final Integer  MAX_HEIGHT = 250;

    public static final String PHONE_NUMBER_REGEX = "^(\\+375|80) \\((29|25|44|33|17)\\) [0-9]{3}-[0-9]{2}-[0-9]{2}$";
    public static final Pattern REGEX_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX, Pattern.CASE_INSENSITIVE);
}
