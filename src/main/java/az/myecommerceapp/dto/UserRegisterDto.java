package az.myecommerceapp.dto;

import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterDto {
    String email;
    String userName;
    String password;
    String repeatPassword;
}