package task.brilloconnetz.InputValidation.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
@Slf4j
@Service
public class JwtUtil {

    static String secretKey = "3979244226452948404D635166546A576D5A7134743777217A25432A462D4A61";
    public static String generateJWT( String userId) {

        long expirationMillis = 3600000; //  1 hour

        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMillis);

        String jwt = Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSignInKey(),SignatureAlgorithm.HS256)
                .compact();

        log.info("JWT {}", jwt);

        return jwt;
    }
    private static Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static String verifyJWT(String jwt, String secretKey) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
            Claims claims = claimsJws.getBody();
            String subject = claims.getSubject();
            return subject;
        } catch (Exception e) {
            return "Verification fails";
        }
    }
}
