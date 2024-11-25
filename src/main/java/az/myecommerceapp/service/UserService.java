package az.myecommerceapp.service;

import az.myecommerceapp.dto.UserLoginDto;
import az.myecommerceapp.dto.UserRegisterDto;
import az.myecommerceapp.dto.UserResetPasswordDto;
import az.myecommerceapp.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);
    void loginUser(UserLoginDto userLoginDto);
    void resetPasswordUser(UserResetPasswordDto userResetPasswordDto);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    void deleteUserById(Long id);
}
