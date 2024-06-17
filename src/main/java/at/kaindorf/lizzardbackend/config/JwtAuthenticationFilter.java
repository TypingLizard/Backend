package at.kaindorf.lizzardbackend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.awt.*;
import java.io.IOException;

/**
 * Project: Typing_Lizard_Backend
 * Author : Alexander Friedl
 * Date : 12.06.2024
 * Time : 16:42
 */


@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        // Get the Authorization header from the request
        final String authHeader = request.getHeader("Authorization");
        final String userName;
        final String jwt;

        // Check if the Authorization header is present and starts with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // If the header is missing or doesn't start with "Bearer ", continue the filter chain
            log.debug("Authorization header not found or does not start with Bearer ");
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the JWT token from the Authorization header
        jwt = authHeader.substring(7);
        log.debug("JWT token extracted: {}", jwt);

        // Extract the username from the JWT token
        userName = jwtService.extractUsername(jwt);
        log.debug("Username extracted from JWT token: {}", userName);

        // Check if the username is not null and there is no existing authentication in the security context
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load user details from the database or other persistent storage
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
            log.debug("User details loaded for username: {}", userName);

            // Validate the token
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // If the token is valid, create an authentication token
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                // Set additional details for the authentication token
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                // Set the authentication in the security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
                log.debug("Authentication token set in security context for username: {}", userName);
            } else {
                log.debug("JWT token is not valid for username: {}", userName);
            }
        } else {
            log.debug("Username is null or authentication is already set in security context");
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}