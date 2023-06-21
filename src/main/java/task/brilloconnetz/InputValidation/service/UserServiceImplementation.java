package task.brilloconnetz.InputValidation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.brilloconnetz.InputValidation.dto.InputDto;
import task.brilloconnetz.InputValidation.exception.BrilloconnetzException;
import task.brilloconnetz.InputValidation.model.BrilloconnetzUser;
import task.brilloconnetz.InputValidation.model.Constant;
import task.brilloconnetz.InputValidation.repository.UserRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImplementation implements UserService{

        @Autowired
        private UserRepository userRepository;
    @Override
    public void validateUser(InputDto dto) {

        validateUserInput(dto);

        BrilloconnetzUser user = BrilloconnetzUser.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .build();

        userRepository.save(user);

    }

    private void validateUserInput(InputDto dto) {
        if(!emailIsValid(dto.getEmail()) || dto.getEmail().isEmpty()) throw new BrilloconnetzException("Email is invalid");

        if(!passwordIsValid(dto.getPassword()) || dto.getEmail().isEmpty()) throw new BrilloconnetzException("strong password with at least 1 upper case, 1 special , 1 number and must be minimum of 8 characters");

        if(dto.getUsername().length() < Constant.USERNAME_MIN_LENGTH || dto.getUsername().isEmpty()) throw new BrilloconnetzException("username must be at greater tha 4");

        LocalDate dob = LocalDate.parse(dto.getDateOfBirth());
        LocalDate curDate = LocalDate.now();
        Period period = Period.between(dob, curDate);
        if(period.getYears() < Constant.AGE_LIMIT){
            throw new BrilloconnetzException("user must be 16 years and above");
        }
    }

    private boolean passwordIsValid(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        Pattern pattern  = Pattern.compile(regex);
        Matcher matcher =  pattern.matcher(password);

        return matcher.matches();
    }
    private boolean emailIsValid(String email) {
        String regex = "[a-zA-z][\\w-]{1,20}@\\w{2,20}\\.\\w{2,3}$";
        Pattern pattern  = Pattern.compile(regex);
        Matcher matcher =  pattern.matcher(email);

        return matcher.matches();
    }
}
