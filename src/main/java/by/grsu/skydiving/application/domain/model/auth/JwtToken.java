package by.grsu.skydiving.application.domain.model.auth;

import by.grsu.skydiving.application.domain.exception.business.TokenVerificationException;
import io.jsonwebtoken.Jwts;
import lombok.Getter;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Getter
public class JwtToken {
    private final String token;

    private JwtToken(String token) {
        this.token = token;
    }

    public JwtToken(String token, SecretKey secretKey) {
        validate(token, secretKey);

        this.token = token;
    }

    private void validate(String token, SecretKey secretKey) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parse(token);
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

        return new JwtToken(jwtString);
    }
}
