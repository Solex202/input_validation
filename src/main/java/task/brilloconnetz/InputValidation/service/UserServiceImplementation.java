package task.brilloconnetz.InputValidation.service;

import org.springframework.stereotype.Service;
import task.brilloconnetz.InputValidation.dto.InputDto;
import task.brilloconnetz.InputValidation.model.BrilloconnetzUser;

@Service
public class UserServiceImplementation implements UserService{
    @Override
    public void validateUser(InputDto dto) {
        BrilloconnetzUser user = new BrilloconnetzUser();

//        if(emailIsValid)
    }
}
