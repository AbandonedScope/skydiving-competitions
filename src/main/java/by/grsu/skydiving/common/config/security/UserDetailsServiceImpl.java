package by.grsu.skydiving.common.config.security;

import by.grsu.skydiving.application.domain.model.auth.UserAuthInfo;
import by.grsu.skydiving.application.port.in.GetUserByLoginUseCase;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final GetUserByLoginUseCase getUserByLoginUseCase;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthInfo userAuthInfo = getUserByLoginUseCase.getByLogin(username);

        return buildFromUserInfo(userAuthInfo);
    }

    private UserDetails buildFromUserInfo(UserAuthInfo userAuthInfo) {
        return new UserDetailsWithId(userAuthInfo);
    }

    public static class UserDetailsWithId implements UserDetails {
        private final String roleName;
        private final String password;
        private final String username;
        @Getter
        private final long id;

        public UserDetailsWithId(UserAuthInfo userAuthInfo) {
            roleName = userAuthInfo.role().name();
            password = userAuthInfo.credentials().password();
            username = userAuthInfo.credentials().login();
            id = userAuthInfo.userId();
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority(roleName));
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
