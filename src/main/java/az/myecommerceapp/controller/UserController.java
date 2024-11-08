package az.myecommerceapp.controller;

import az.myecommerceapp.dto.UserLoginDto;
import az.myecommerceapp.dto.UserRegisterDto;
import az.myecommerceapp.dto.UserResetPasswordDto;
import az.myecommerceapp.repository.UserRepository;
import az.myecommerceapp.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/v1")
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



}
