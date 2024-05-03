package by.grsu.skydiving.application.domain.model.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

public class JwtToken {
    @Getter
    private final String token;
    private final SecretKey secretKey;

    public JwtToken(String token, SecretKey key) {
        this.token = token;
        this.secretKey = key;

        validate();
    }

    private JwtToken(SecretKey key, String token) {
        this.token = token;
        this.secretKey = key;

        validate();
    }


    public String extractLogin() {
        return extractClaims()
                .getSubject();
    }

    private void validate() {
        if (isTokenExpired()) {
            throw new TokenExpiredException();
        }
    }

    private boolean isTokenExpired() {
        return extractClaims()
                .getExpiration().before(new Date());
    }

    private Claims extractClaims() {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception ex) {
            throw new TokenVerificationException(ex);
        }
    }

    public static JwtToken of(JwtTokenGenerationSettings info) {
        String jwtString = Jwts.builder()
                .issuer(info.issuer())
                .subject(info.subject())
                .claim("id", info.userId())
                .claim("role", info.userRole())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(info.ttl())))
                .signWith(info.secretKey())
                .compact();

        return new JwtToken(info.secretKey(), jwtString);
    }
}
