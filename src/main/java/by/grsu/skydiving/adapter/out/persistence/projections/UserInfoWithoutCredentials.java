package by.grsu.skydiving.adapter.out.persistence.projections;

public interface UserInfoWithoutCredentials {
    Long getUserId();
    String getFirstName();
    String getSecondName();
    String getPatronymic();
    short getRole();
}
