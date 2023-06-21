package task.brilloconnetz.InputValidation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrilloconnetzUser {
    @Id
    private String id;
    private String email;

    private String username;
    private String password;
    private LocalDate dateOfBirth;

}
