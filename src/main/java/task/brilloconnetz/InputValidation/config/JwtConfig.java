package task.brilloconnetz.InputValidation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import task.brilloconnetz.InputValidation.util.JwtUtil;

@Configuration
public class JwtConfig {

    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil(jwtSecretKey);
    }
}
