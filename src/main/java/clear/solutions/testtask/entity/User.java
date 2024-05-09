package clear.solutions.testtask.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

// model, which will communicate with DB
@Data
@NoArgsConstructor
public class User {

    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String address;
    private String phone;

}