package by.grsu.skydiving.application.port.out;

public interface ExistsUserByLoginPort {
    boolean existsByLoginAndNotWithId(String login, long userId);

    boolean existsByLogin(String login);
}
