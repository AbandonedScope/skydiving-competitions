package by.grsu.skydiving.adapter.out.persistence.projections;

public interface UserInfoWithoutCredentials {
    Integer getUserId();

    String getFirstName();

    String getSecondName();

    String getPatronymic();

    short getRole();
}
