package by.grsu.skydiving.application.domain.service;

import by.grsu.skydiving.application.domain.model.auth.UserCredentials;
import by.grsu.skydiving.application.domain.model.skydiver.FullName;
import by.grsu.skydiving.application.port.in.GenerateUserCredentialsUseCase;
import by.grsu.skydiving.application.port.out.ExistsUserByLoginPort;
import by.grsu.skydiving.common.UseCase;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GenerateUserCredentialsService implements GenerateUserCredentialsUseCase {
    private final ExistsUserByLoginPort existsUserByLoginPort;
    private final Random random = new Random();

    @Override
    public UserCredentials generate(GenerateCredentialsCommand command) {
        int length = 1;
        Long userId = command.userId();
        String generatedLogin = generateLogin(command, length);
        if (userId != null) {
            while (existsUserByLoginPort.existsByLoginAndNotWithId(generatedLogin, userId)) {
                length++;
                generatedLogin = generateLogin(command, length);
            }
        } else {
            while (existsUserByLoginPort.existsByLogin(generatedLogin)) {
                length++;
                generatedLogin = generateLogin(command, length);
            }
        }

        String generatedPassword = generateSecureRandomPassword();

        return new UserCredentials(generatedLogin, generatedPassword);
    }

    private String generateLogin(GenerateCredentialsCommand command, int length) {
        FullName fullName = command.fullName();
        String firstName = fullName.firstName();
        String patronymic = fullName.patronymic();

        String loginSecondNamePart = fullName.secondName();
        StringBuilder loginFirstNamePart = new StringBuilder(length);
        StringBuilder loginPatronymicPart = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            if (firstName.length() > i) {
                loginFirstNamePart.append(firstName.charAt(i));
            }

            if (patronymic.length() > i) {
                loginPatronymicPart.append(patronymic.charAt(i));
            }

            if (length > firstName.length() && length > patronymic.length()) {
                loginPatronymicPart.append(getRandomAlphabets(1, false).findFirst().get());
            }
        }


        String russianLogin = loginSecondNamePart.concat("." + loginFirstNamePart).concat("." + loginPatronymicPart);

        return convertCyrillic(russianLogin);
    }

    private String convertCyrillic(String login) {
        char[] abcCyr =
            {' ', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т',
                'у', 'ф', 'х', 'ц', 'ч', 'ш', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н',
                'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
                'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '1', '2', '3', '4', '5', '6', '7', '8', '9', '/', '-', '.'};
        String[] abcLat =
            {" ", "a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "j", "k", "l", "m", "n", "o", "p", "r", "s", "t",
                "u", "f", "h", "c", "ch", "sh", "A", "B", "V", "G", "D", "E", "E", "Zh", "Z", "I", "J", "K", "L", "M",
                "N", "O", "P", "R", "S", "T", "U", "F", "H", "C", "Ch", "Sh", "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
                "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
                "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "/", "-", "."};

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < login.length(); i++) {
            for (int x = 0; x < abcCyr.length; x++) {
                if (login.charAt(i) == abcCyr[x]) {
                    builder.append(abcLat[x]);
                }
            }
        }

        return builder.toString();
    }

    public String generateSecureRandomPassword() {
        Stream<Character> pwdStream = Stream.concat(getRandomNumbers(2),
            Stream.concat(getRandomSpecialChars(2),
                Stream.concat(getRandomAlphabets(2, true), getRandomAlphabets(4, false))));
        List<Character> charList = pwdStream.collect(Collectors.toList());
        Collections.shuffle(charList);
        return charList.stream()
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString();
    }

    public Stream<Character> getRandomAlphabets(int count, boolean upperCase) {
        IntStream characters;
        if (upperCase) {
            characters = random.ints(count, 65, 90);
        } else {
            characters = random.ints(count, 97, 122);
        }

        return characters.mapToObj(data -> (char) data);
    }

    public Stream<Character> getRandomNumbers(int count) {
        IntStream numbers = random.ints(count, 48, 57);
        return numbers.mapToObj(data -> (char) data);
    }

    public Stream<Character> getRandomSpecialChars(int count) {
        IntStream specialChars = IntStream.generate(() -> random.nextInt(33, 45))
            .filter(data -> data != 34 && data != 39)
            .limit(count);

        return specialChars
            .mapToObj(data -> (char) data);
    }
}
