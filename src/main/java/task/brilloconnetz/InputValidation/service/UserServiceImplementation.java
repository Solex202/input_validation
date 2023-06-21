package task.brilloconnetz.InputValidation.service;

import org.modelmapper.ModelMapper;
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

        @Autowired
        private ModelMapper mapper;
    @Override
    public boolean validateUser(InputDto dto) {

        validateUserInput(dto);

        BrilloconnetzUser user = mapper.map(dto, BrilloconnetzUser.class);
        userRepository.save(user);

        return true;

    }
    private void validateUserInput(InputDto dto) {
        StringBuilder builder = new StringBuilder();
        if(emailAlreadyExist(dto.getEmail())) builder.append(Constant.EMAIL_EXCEPTION_MESSAGE_1).append("\n");

        if(usernameAlreadyExist(dto.getUsername()))  builder.append(Constant.USERNAME_EXCEPTION_MESSAGE).append("\n");

        if(!emailIsValid(dto.getEmail()) || dto.getEmail().isEmpty()) builder.append(Constant.EMAIL_EXCEPTION_MESSAGE_2).append("\n");

        if(!passwordIsValid(dto.getPassword()) || dto.getPassword().isEmpty()) builder.append(Constant.PASSWORD_EXCEPTION_MESSAGE).append("\n");

        if(dto.getUsername().length() < Constant.USERNAME_MIN_LENGTH || dto.getUsername().isEmpty()) builder.append(Constant.USERNAME_EXCEPTION_MESSAGE_2).append("\n");

        LocalDate dob = LocalDate.parse(dto.getDateOfBirth());
        LocalDate curDate = LocalDate.now();
        Period period = Period.between(dob, curDate);
        if(period.getYears() < Constant.AGE_LIMIT) builder.append(Constant.AGE_EXCEPTION_MESSAGE);


       if (!builder.isEmpty())throw new BrilloconnetzException(builder.toString());
    }
    private boolean usernameAlreadyExist(String username) {
        return userRepository.existsByUsername(username);
    }
    private boolean emailAlreadyExist(String email) {
        return userRepository.existsByEmail(email);
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
