package com.daniel.gestiondestock.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.daniel.gestiondestock.services.auth.JwtService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException, java.io.IOException {
    final String authHeader = request.getHeader("authorization");
    final String jwt;
    final String userEmail;

    if (authHeader == null || !authHeader.startsWith("Jwt ")) {
      filterChain.doFilter(request, response);
      return;
    }
    ;
    jwt = authHeader.substring(4);
    userEmail = jwtService.extraUsername(jwt);
    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() != null) {
      var user = this.userDetailsService.loadUserByUsername(userEmail);
      if (this.jwtService.isTokenValid(jwt, user)) {
        var authToken = new UsernamePasswordAuthenticationToken(userEmail, null, user.getAuthorities());
        authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

      }

    }

  }

}
