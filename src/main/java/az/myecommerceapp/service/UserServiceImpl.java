package az.myecommerceapp.service;

import az.myecommerceapp.dto.UserLoginDto;
import az.myecommerceapp.dto.UserRegisterDto;
import az.myecommerceapp.dto.UserResetPasswordDto;
import az.myecommerceapp.entity.User;
import az.myecommerceapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public void registerUser(UserRegisterDto userRegisterDto) {
        User user = modelMapper.map(userRegisterDto, User.class);

        if (userRepository.existsByEmail(userRegisterDto.getEmail())) {
            throw new RuntimeException("Email Already exists");
        }
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getRepeatPassword())) {
            throw new RuntimeException("Repeat Password Correctly");
        }
        userRepository.save(user);
    }

    public boolean loginUser(UserLoginDto userLoginDto) {
        User user = userRepository.findByUserName(userLoginDto.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!user.getPassword().equals(userLoginDto.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        return true;
    }

    public void resetPasswordUser(UserResetPasswordDto userResetPasswordDto){
        User user = userRepository.findByEmail(userResetPasswordDto.getEmail());
        if (user != null) {
            if (!user.getPassword().equals(userResetPasswordDto.getOldPassword())){
                throw new RuntimeException("Wrong password");
            }
            user.setPassword(userResetPasswordDto.getNewPassword());
            userRepository.save(user);
        }
        else {
            throw new RuntimeException("User not found");
        }
    }

}
