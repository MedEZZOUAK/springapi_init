package ensate.ma.SpringAPI.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import ensate.ma.SpringAPI.config.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import ensate.ma.SpringAPI.user.User;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
          @NonNull HttpServletRequest request,
          @NonNull HttpServletResponse response,
          @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    try {
      final String authorizationHeader = request.getHeader("Authorization");

      if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
      }

      try {
        final String jwt = authorizationHeader.substring(7);
        final String email = jwtService.extractEmail(jwt);

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          User userdetails = (User) this.userDetailsService.loadUserByUsername(email);
          if (jwtService.isTokenValid(jwt, userdetails)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userdetails,
                    null,
                    userdetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
          } else {
            handleError(response, "JWT expired");
            return;
          }
        }
      } catch (ExpiredJwtException e) {
        handleError(response, "JWT expired");
        return;
      }

      filterChain.doFilter(request, response);

    } catch (Exception e) {
      handleError(response, "JWT expired");
    }
  }

  private void handleError(HttpServletResponse response, String message) throws IOException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    Map<String, String> error = new HashMap<>();
    error.put("error", message);

    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(response.getOutputStream(), error);
  }
}