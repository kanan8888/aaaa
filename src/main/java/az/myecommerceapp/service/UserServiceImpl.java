package az.myecommerceapp.service;

import az.myecommerceapp.dto.UserLoginDto;
import az.myecommerceapp.dto.UserRegisterDto;
import az.myecommerceapp.dto.UserResetPasswordDto;
import az.myecommerceapp.entity.User;
import az.myecommerceapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
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

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        User user = userRepository.findByUserNameAndPassword(userLoginDto.getUserName(), userLoginDto.getPassword());
        if (user == null ) {
            throw new RuntimeException("Wrong username or password");
        }
    }

    @Override
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

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userRepository.findById(id))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }



}
