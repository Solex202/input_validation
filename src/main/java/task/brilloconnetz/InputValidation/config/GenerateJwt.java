package task.brilloconnetz.InputValidation.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class GenerateJwt {

    public static String generateJWT() {
        // Generate the JWT with a secret key
        String secretKey = "yourSecretKey"; // Replace with your secret key
        String subject = "userId123"; // Replace with the user ID or relevant information
        long expirationMillis = 3600000; // Token expiration time in milliseconds (e.g., 1 hour)

        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMillis);

        String jwt = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return jwt;
    }
}
