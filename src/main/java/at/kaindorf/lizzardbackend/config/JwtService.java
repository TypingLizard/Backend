package at.kaindorf.lizzardbackend.config;

import at.kaindorf.lizzardbackend.pojos.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Project: Typing_Lizard_Backend
 * Author : Alexander Friedl
 * Date : 12.06.2024
 * Time : 16:51
 */

@Service
@Slf4j
public class JwtService {


    // a secret key that is used for encoding
    private static final String SECRET_KEY = "9e63b71f8a7e3de62e0a303affdb328a664122489c7d88342313b104abe5d5c9";


    /**
     *  extract the username from the token
     * @param token
     * @return
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    /**
     * the function extracts a specific claim from the token
     * @param token
     * @param claimsResolver
     * @return
     * @param <T>
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    /**
     * the function generates a user if you don't already have a user
     * @param user
     * @return
     */
    public String generateToken(UserDetails user) {
        log.info("generating token");
        return generateToken(new HashMap<>(), user);
    }


    /**
     * the function generates a token with additional claims for the given user
     * @param extractClaims
     * @param user
     * @return
     */
    public String generateToken(Map<String, Object> extractClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSinginKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * the function validates the token against the given user details
     * @param token
     * @param user
     * @return
     */
    public boolean isTokenValid(String token, UserDetails user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }


    /**
     * the function checks if the token is expired
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * the function extracts the expiration date from the token
     * @param token
     * @return
     */
    private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
    }

    /**
     * the function extracts all claims from the token
     * @param token
     * @return
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSinginKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    private Key getSinginKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
