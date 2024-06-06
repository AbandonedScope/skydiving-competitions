package by.grsu.skydiving.common.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
        }
    }
}