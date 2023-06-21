package task.brilloconnetz.InputValidation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class InputDto {

    private String email;
    private String username;
    private String password;
    private String dateOfBirth ;

}
