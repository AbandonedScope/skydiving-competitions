package by.grsu.skydiving.application.domain.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record PhoneNumber(
        String number
) {
    public static final String PHONE_NUMBER_REGEX = "^(\\+375|80) \\((29|25|44|33|17)\\) [0-9]{3}-[0-9]{2}-[0-9]{2}$";
    public static final Pattern REGEX_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX, Pattern.CASE_INSENSITIVE);

    public PhoneNumber {
        Matcher matcher = REGEX_PATTERN.matcher(number);
        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }
    }
}
