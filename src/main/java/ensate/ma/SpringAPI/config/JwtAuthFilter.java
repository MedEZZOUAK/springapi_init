package ensate.ma.SpringAPI.config;

import ensate.ma.SpringAPI.user.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request,
    @NonNull  HttpServletResponse response,
    @NonNull  FilterChain filterChain
  ) throws ServletException, IOException {
    final String authorizationHeader = request.getHeader("Authorization");
    final String jwt;
    final String email;
    if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    jwt = authorizationHeader.substring(7);
    email = jwtService.extractEmail(jwt);
    if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      User userdetails= (User) this.userDetailsService.loadUserByUsername(email);
      if(jwtService.isTokenValid(jwt, userdetails)) {
        UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(userdetails,
          null,
          userdetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

      }
    }
    filterChain.doFilter(request, response);

  }
}
