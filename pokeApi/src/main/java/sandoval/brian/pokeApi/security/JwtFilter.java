package sandoval.brian.pokeApi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sandoval.brian.pokeApi.dto.UserPermission;
import sandoval.brian.pokeApi.service.JwtService;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, Origin, X-Requested");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        if (isPublicRoute(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sedUnauthorized(response, "Se requiere token de autorizacion");
            return;
        }

        String token = authHeader = authHeader.substring(7);

        if (!jwtService.isTokenValid(token)) {
            sedUnauthorized(response, "Token invalido o expirado");
            return;
        }

        UserPermission user = jwtService.extractUser(token);
        request.setAttribute("user", user.getEmail());
        request.setAttribute("role", user.getRole());

        filterChain.doFilter(request, response);
    }

    private void sedUnauthorized(HttpServletResponse response, String mensaje) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("aplication/json");
        response.setCharacterEncoding("UTF-8");

        String error = String.format("{\"error\": \"%s\"", mensaje);
        response.getWriter().write(error);
    }

    private boolean isPublicRoute(HttpServletRequest request) {
        String method = request.getMethod();
        String path = request.getRequestURI();

        if (path.equals("/api/auth/login") && method.equals("POST")) return true;

        if (path.equals("/api/users") && method.equals("POST")) return true;

        if (path.equals("/api/pokemones") && method.equals("GET")) return true;

        if (path.equals("/api/pokemones/*") && method.equals("GET")) return true;

        return false;
    }
}
