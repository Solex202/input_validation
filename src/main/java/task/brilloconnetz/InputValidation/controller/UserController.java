package task.brilloconnetz.InputValidation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.brilloconnetz.InputValidation.dto.InputDto;
import task.brilloconnetz.InputValidation.exception.BrilloconnetzException;
import task.brilloconnetz.InputValidation.service.UserService;

import java.text.ParseException;

@RestController
@RequestMapping("/verification")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/verify-user")
    public ResponseEntity<?> verifyUser(@RequestBody InputDto dto){
        try{
            String response = userService.registerUser(dto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BrilloconnetzException | ParseException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
