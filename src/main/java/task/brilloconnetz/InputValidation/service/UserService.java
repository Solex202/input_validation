package task.brilloconnetz.InputValidation.service;

import task.brilloconnetz.InputValidation.dto.InputDto;

public interface UserService {
    boolean validateUser(InputDto dto);
}
