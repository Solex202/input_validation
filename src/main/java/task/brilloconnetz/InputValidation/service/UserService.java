package task.brilloconnetz.InputValidation.service;

import task.brilloconnetz.InputValidation.dto.InputDto;

import java.text.ParseException;

public interface UserService {
    String registerUser(InputDto dto) throws ParseException;
}
