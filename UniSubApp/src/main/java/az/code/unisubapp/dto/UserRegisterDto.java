package az.code.unisubapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterDto {
    private long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String password;
}
