package task.brilloconnetz.InputValidation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import task.brilloconnetz.InputValidation.dto.InputDto;
import task.brilloconnetz.InputValidation.exception.BrilloconnetzException;
import task.brilloconnetz.InputValidation.service.UserService;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testThatCanCreateUser(){
        InputDto dto =  InputDto.builder()
                .email("onwukalotachukwu210@gmail.com")
                .dateOfBirth(String.valueOf(LocalDate.of(2002, 6,22)))
                .password("#Solom9n123")
                .username("solomon")
                .build();

        userService.validateUser(dto);

        assertAll(
                ()-> assertNotNull(dto),
                ()-> assertThat(dto.getEmail(), is("onwukalotachukwu210@gmail.com"))
        );

    }

    @Test
    @DisplayName("throw exception if email is invalid")
    void testThatCannotCreateUser(){
        InputDto dto =  InputDto.builder()
                .email(".onwukalotachukwu210@gmail.com")
//                .dateOfBirth(LocalDate.of(2000, 3,22))
                .password("#Solom9n123")
                .username("solomon")
                .build();

        assertThrows(BrilloconnetzException.class,()-> userService.validateUser(dto));
    }

    @Test
    @DisplayName("throw exception if password is invalid")
    void testThatCannotCreateUser_if_password_is_invalid(){
        InputDto dto =  InputDto.builder()
                .email("onwukalotachukwu210@gmail.com")
//                .dateOfBirth("21-02-2012")
                .password("#solom9n123")
                .username("solomon")
                .build();

        assertThrows(BrilloconnetzException.class,()-> userService.validateUser(dto));
    }

    @Test
    @DisplayName("throw exception if username is less than 4")
    void testThatCannotCreateUser_if_username_is_invalid(){
        InputDto dto =  InputDto.builder()
                .email("onwukalotachukwu210@gmail.com")
//                .dateOfBirth(LocalDate.of(2000, 3,22))
                .password("#solom9n123")
                .username("sol")
                .build();

        assertThrows(BrilloconnetzException.class,()-> userService.validateUser(dto));
    }
}
