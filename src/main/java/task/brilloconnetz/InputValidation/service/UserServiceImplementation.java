package task.brilloconnetz.InputValidation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.brilloconnetz.InputValidation.dto.InputDto;
import task.brilloconnetz.InputValidation.exception.BrilloconnetzException;
import task.brilloconnetz.InputValidation.model.BrilloconnetzUser;
import task.brilloconnetz.InputValidation.repository.UserRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImplementation implements UserService{

        @Autowired
        private UserRepository userRepository;
    @Override
    public void validateUser(InputDto dto) {

        if(!emailIsValid(dto.getEmail()) || dto.getEmail().isEmpty()) throw new BrilloconnetzException("Email is invalid");

        if(!passwordIsValid(dto.getPassword()) || dto.getEmail().isEmpty()) throw new BrilloconnetzException("Password must contain at least 1 uppercase,1 lowercase ");

        if(dto.getUsername().length() < 4 || dto.getUsername().isEmpty()) throw new BrilloconnetzException("username must be at greater tha 4");

//        if(dto.getDateOfBirth()
        BrilloconnetzUser user = BrilloconnetzUser.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .build();

        userRepository.save(user);


    }

    private boolean passwordIsValid(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        Pattern pattern  = Pattern.compile(regex);
        Matcher matcher =  pattern.matcher(password);

        return matcher.matches();
    }

    private boolean emailIsValid(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern  = Pattern.compile(regex);
        Matcher matcher =  pattern.matcher(email);

        return matcher.matches();

    }
}
