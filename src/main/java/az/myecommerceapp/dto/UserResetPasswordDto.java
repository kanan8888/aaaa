package az.myecommerceapp.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResetPasswordDto {
    String email;
    String oldPassword;
    String newPassword;
}
