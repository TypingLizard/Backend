package at.kaindorf.lizzardbackend.auth;

import at.kaindorf.lizzardbackend.config.JwtService;
import at.kaindorf.lizzardbackend.database.UserRepository;
import at.kaindorf.lizzardbackend.pojos.Role;
import at.kaindorf.lizzardbackend.pojos.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Project: Typing_Lizzard_Backend
 * Author : Alexander Friedl
 * Date : 12.06.2024
 * Time : 17:53
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {


    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("Authenticating user in authenticate: " + request.getUsername());

        try {
            log.info("HALLO");

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            log.error("Authentication failed", e);
            throw e;
        }


                var user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var jwtToken = jwtService.generateToken(user);
        System.out.println("Generated JWT Token: " + jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
