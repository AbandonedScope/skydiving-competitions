package by.grsu.skydiving.adapter.out.persistence.entity;

import by.grsu.skydiving.adapter.out.persistence.entity.projection.UserInfoWithoutCredentials;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("user_info_view")
public class UserInfoEntity implements UserInfoWithoutCredentials {
    @Id
    @Column("id")
    private Long userId;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String patronymic;
    private short role;
}
