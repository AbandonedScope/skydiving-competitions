package by.grsu.skydiving.common.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
        }
    }
}