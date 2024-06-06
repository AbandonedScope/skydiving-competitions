package by.grsu.skydiving.application.domain.service.utils;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SecretKeyUtils {
    public static SecretKey getSigningKey(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
