package task.brilloconnetz.InputValidation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import task.brilloconnetz.InputValidation.dto.InputDto;
import task.brilloconnetz.InputValidation.service.UserService;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    void testThatCanCreateUser(){
        InputDto dto =  InputDto.builder()
                .email("")
                .dateOfBirth("21-02-2012")
                .password("solo")
                .username("solomon")
                .build();

        userService.validateUser(dto);
    }
}
