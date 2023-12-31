package task.brilloconnetz.InputValidation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import task.brilloconnetz.InputValidation.dto.InputDto;
import task.brilloconnetz.InputValidation.exception.BrilloconnetzException;
import task.brilloconnetz.InputValidation.service.UserService;

import java.text.ParseException;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    void testThatCanCreateUser() throws ParseException {
        String val = String.valueOf(LocalDate.of(2002, 6,22));
        InputDto dto =  InputDto.builder()
                .email("ammmakalotachukwu210@gmail.com")
                .dateOfBirth(String.valueOf(LocalDate.of(2002, 6,22)))
                .password("#Solom9n123")
                .username("deeaswegg")
                .build();

        log.info("VALUE {}", val);

        String result = userService.registerUser(dto);

        assertAll(
                ()-> assertNotNull(dto),
                ()-> assertThat(dto.getEmail(), is("ammmakalotachukwu210@gmail.com")),
                ()-> assertEquals(result, "Verification passed")
        );

    }

    @Test
    @DisplayName("throw exception if email is invalid")
    void testThatCannotCreateUser(){
        InputDto dto =  InputDto.builder()
                .email(",onwukalotachukwu210@gmail.com")
                .dateOfBirth(String.valueOf(LocalDate.of(2000, 3,22)))
                .password("#Solom9n123")
                .username("solomon")
                .build();

        assertThrows(BrilloconnetzException.class,()-> userService.registerUser(dto));
    }

    @Test
    @DisplayName("throw exception if email is invalid")
    void testThatCannotCreateUser2(){
        InputDto dto =  InputDto.builder()
                .email("onwukalotachukwu210gmailcom")
                .dateOfBirth(String.valueOf(LocalDate.of(2000, 3,22)))
                .password("#Solom9n123")
                .username("solomon")
                .build();

        assertThrows(BrilloconnetzException.class,()-> userService.registerUser(dto));
    }

    @Test
    @DisplayName("throw exception if password is invalid")
    void testThatCannotCreateUser_if_password_is_invalid(){
        InputDto dto =  InputDto.builder()
                .email("onwukalotachukwu210@gmail.com")
                .dateOfBirth(String.valueOf(LocalDate.of(2000, 3,22)))
                .password("#solom9n123")
                .username("solomon")
                .build();

        assertThrows(BrilloconnetzException.class,()-> userService.registerUser(dto));
    }

    @Test
    @DisplayName("throw exception if username is less than 4")
    void testThatCannotCreateUser_if_username_is_invalid(){
        InputDto dto =  InputDto.builder()
                .email("onwukalotachukwu210@gmail.com")
                .dateOfBirth(String.valueOf(LocalDate.of(2000, 3,22)))
                .password("#solom9n123")
                .username("sol")
                .build();

        assertThrows(BrilloconnetzException.class,()-> userService.registerUser(dto));
    }

    @Test
    @DisplayName("throw exception if age is less than 16, age should be greater than or equal to 16")
    void testThatCannotCreateUser_if_ageLimit_is_invalid(){
        InputDto dto =  InputDto.builder()
                .email("onwukalotachukwu210@gmail.com")
                .dateOfBirth(String.valueOf(LocalDate.of(2015, 3,22)))
                .password("#solom9n123")
                .username("sol")
                .build();

        assertThrows(BrilloconnetzException.class,()-> userService.registerUser(dto));
    }

    @Test
    @DisplayName("throw exception if email already exist in database")
    void testThatCannotCreateUser3(){
        InputDto dto =  InputDto.builder()
                .email("onwukalotachukwu210@gmail.com")
                .dateOfBirth(String.valueOf(LocalDate.of(2000, 3,22)))
                .password("#Solom9n123")
                .username("chima")
                .build();

        assertThrows(BrilloconnetzException.class,()-> userService.registerUser(dto));
    }

    @Test
    @DisplayName("throw exception if username already in use")
    void testThatCannotCreateUser4(){
        InputDto dto =  InputDto.builder()
                .email("glory222@gmail.com")
                .dateOfBirth(String.valueOf(LocalDate.of(2000, 3,22)))
                .password("#Thanks2187")
                .username("solomon")
                .build();

        assertThrows(BrilloconnetzException.class,()-> userService.registerUser(dto));
    }

}
