package com.example.saleswebsite.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.accessExpirationMinutes:15}")
    private long accessMinutes;

    @Value("${security.jwt.refreshExpirationDays:7}")
    private long refreshDays;

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret.getBytes());
    }

    public String generateAccessToken(String username, String role) {
        Instant now = Instant.now();
        return JWT.create()
                .withSubject(username)
                .withClaim("role", role)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plus(accessMinutes, ChronoUnit.MINUTES)))
                .sign(getAlgorithm());
    }

    public String generateRefreshToken(String username) {
        Instant now = Instant.now();
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plus(refreshDays, ChronoUnit.DAYS)))
                .sign(getAlgorithm());
    }

    public DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(getAlgorithm()).build();
        return verifier.verify(token);
    }

    public String getUsernameFromToken(String token) {
        return verifyToken(token).getSubject();
    }
}
