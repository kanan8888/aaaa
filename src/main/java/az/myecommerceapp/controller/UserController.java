package az.myecommerceapp.controller;

import az.myecommerceapp.dto.UserLoginDto;
import az.myecommerceapp.dto.UserRegisterDto;
import az.myecommerceapp.dto.UserResetPasswordDto;
import az.myecommerceapp.entity.User;
import az.myecommerceapp.repository.UserRepository;
import az.myecommerceapp.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterDto> register(@RequestBody UserRegisterDto userRegisterDto) {
        userServiceImpl.registerUser(userRegisterDto);
        return new ResponseEntity<>(userRegisterDto, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<UserLoginDto> login(@RequestBody UserLoginDto userLoginDto) {
        userServiceImpl.loginUser(userLoginDto);
        return new ResponseEntity<>(userLoginDto, HttpStatus.OK);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody UserResetPasswordDto userResetPasswordDto) {
        userServiceImpl.resetPasswordUser(userResetPasswordDto);
        return ResponseEntity.ok("Password reset successfully");
    }

    @GetMapping("get-all")
    public List<User> getAll() {
        return userServiceImpl.getAllUsers();
    }
    @GetMapping("/get-by-id/{id}")
    public Optional<User> getById(@PathVariable Long id) {
        return userServiceImpl.getUserById(id);
    }

    @DeleteMapping("/delete-user-by-id/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userServiceImpl.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully");
    }


}





