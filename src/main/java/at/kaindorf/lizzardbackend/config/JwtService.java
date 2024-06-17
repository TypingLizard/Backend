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


    // extract the username from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    // extract a specific claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // generate a token for the given user
    public String generateToken(UserDetails user) {
        log.info("generating token");
        return generateToken(new HashMap<>(), user);
    }


    // generate a token with additional claims for the given user
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

    // validate the token against the given user details
    public boolean isTokenValid(String token, UserDetails user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }

    // check if the token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // extract the expiration date from the token
    private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
    }

    // extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSinginKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // get the signing key for encoding/decoding the token
    private Key getSinginKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
