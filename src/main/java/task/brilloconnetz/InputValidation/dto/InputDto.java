package task.brilloconnetz.InputValidation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InputDto {

    private String email;
    private String username;
    private String password;
    private String dateOfBirth;

}
