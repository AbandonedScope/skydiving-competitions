package by.grsu.skydiving.common.config.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import by.grsu.skydiving.application.domain.model.auth.UserRole;
import by.grsu.skydiving.common.JwtAuthenticationFilter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsService userDetailsService;

    private static final String[] permitAllEndpoints = new String[] {

    };

    private static final String[] authenticatedOnlyEndpoints = new String[] {

    };

    private static final String[] adminOnlyEndpoints = new String[] {
        ,


    };

    private static final String[] secretaryOnlyEndpoints = new String[] {
        "/api/v1/referees/**"
    };

    private static final String[] refereeOnlyEndpoints = new String[] {

    };

    private static final String[] adminAndSecretaryEndpoints = new String[] {

    };

    private static final String ADMIN_ROLE = UserRole.ADMIN.name();
    private static final String REFEREE_ROLE = UserRole.REFEREE.name();
    private static final String SECRETARY_ROLE = UserRole.SECRETARY.name();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationProvider authenticationProvider)
        throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .cors(this::corsConfigurer)
            .authorizeHttpRequests(request -> request
                .requestMatchers(HttpMethod.GET,
                    "/api/v1/skydivers/{skydiverId}").hasRole(ADMIN_ROLE)
                .requestMatchers(HttpMethod.POST,
                    "/api/v1/auth/regen/{userId}",
                    "/api/v1/auth/sign-up",
                    "/api/v1/referees",
                    "/api/v1/skydivers").hasRole(ADMIN_ROLE)
                .requestMatchers(HttpMethod.PUT,
                    "/api/v1/skydivers/{skydiverId}").hasRole(ADMIN_ROLE)
                .requestMatchers(HttpMethod.DELETE,
                    "/api/v1/referees/{refereeId}",
                    "/api/v1/skydivers/{skydiverId}").hasRole(ADMIN_ROLE)

                .requestMatchers(HttpMethod.GET,
                    "/api/v1/referees/page",
                    "/api/v1/skydivers/page",
                    "/api/v1/knowledge-base").hasAnyRole(ADMIN_ROLE, SECRETARY_ROLE)
                .requestMatchers(HttpMethod.PUT,
                    "/api/v1/skydivers/external/**").hasAnyRole(ADMIN_ROLE, SECRETARY_ROLE)

                .requestMatchers(HttpMethod.GET,
                    "/api/v1/referees/{competitionId}",
                    "/api/v1/competitions",
                    "/api/v1/competitions/{competitionId}",
                    "/api/v1/teams/{competitionId}/members",
                    "/api/v1/jumping/competition/{competitionId}/skydiver/{skydiverId}",
                    "/api/v1/jumping/competition/{competitionId}/skydiver/{skydiverId}/list",
                    "/api/v1/pivot-table/{competitionId}").hasRole(SECRETARY_ROLE)
                .requestMatchers(HttpMethod.POST,
                    "/api/v1/skydivers/external",
                    "/api/v1/competitions/initial",
                    "/api/v1/competitions/{competitionId}",
                    "/api/v1/teams/competition/{competitionId}",
                    "/api/v1/trick-refereeing",
                    "/api/v1/individuals/competition/{competitionId}",
                    "/api/v1/jumping/competition/{competitionId}").hasRole(SECRETARY_ROLE)
                .requestMatchers(HttpMethod.PUT,
                    "/api/v1/competitions/{competitionId}",
                    "/api/v1/competitions/{competitionId}/collegium",
                    "/api/v1/teams/{teamId}/competition/{competitionId}",
                    "/api/v1/teams/{teamId}/competition/{competitionId}/exchange",
                    "/api/v1/jumping/competition/{competitionId}/skydiver/{skydiverId}").hasRole(SECRETARY_ROLE)
                .requestMatchers(HttpMethod.DELETE,
                    "/api/v1/referees/{refereeId}/competition/{competitionId}",
                    "/api/v1/competitions/{competitionId}",
                    "/api/v1/teams/{teamId}/competition/{competitionId}",
                    "/api/v1/individuals/{individualId}/competition/{competitionId}",
                    "/api/v1/jumping/{jumpingId}/competition/{competitionId}").hasRole(SECRETARY_ROLE)

                .requestMatchers(HttpMethod.GET,
                    "/api/v1/trick-refereeing/current").hasRole(REFEREE_ROLE)
                .requestMatchers(HttpMethod.POST,
                    "/api/v1/trick-refereeing/attempts").hasRole(REFEREE_ROLE)

                .requestMatchers(HttpMethod.GET,
                    "/api/v1/auth/user-info").authenticated()
                .anyRequest().authenticated()
            )
            .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(configurer -> configurer
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler())
            );
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
        throws Exception {
        return config.getAuthenticationManager();
    }

    private void corsConfigurer(CorsConfigurer<HttpSecurity> cors) {
        cors.configurationSource(request -> {
            var corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedOriginPatterns(List.of("*"));
            corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            corsConfiguration.setAllowedHeaders(List.of("*"));
            corsConfiguration.setAllowCredentials(true);
            return corsConfiguration;
        });
    }
}
