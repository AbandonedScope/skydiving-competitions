package by.grsu.skydiving.common.config;

import by.grsu.skydiving.application.domain.exception.UserNotFoundException;
import by.grsu.skydiving.application.domain.model.UserInfo;
import by.grsu.skydiving.application.port.in.FindUserByLoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final FindUserByLoginUseCase findUserByLoginUseCase;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = findUserByLoginUseCase.findByLogin(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return buildFromUserInfo(userInfo);
    }

    private UserDetails buildFromUserInfo(UserInfo userInfo) {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority(userInfo.role().name()));
            }

            @Override
            public String getPassword() {
                return userInfo.credentials().password();
            }

            @Override
            public String getUsername() {
                return userInfo.credentials().login();
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
        };
    }
}
