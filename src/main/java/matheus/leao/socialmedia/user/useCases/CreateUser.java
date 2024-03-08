package matheus.leao.socialmedia.user.useCases;

import matheus.leao.socialmedia.user.User;
import matheus.leao.socialmedia.user.UserRepository;
import matheus.leao.socialmedia.user.dtos.CreateUserDto;
import matheus.leao.socialmedia.user.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserDto execute(CreateUserDto createUserDto) {
        User newUser = new User(createUserDto.nickname(), createUserDto.name(), createUserDto.email(), createUserDto.password());
        userRepository.save(newUser);
        return newUser.toDto();
    }

    public void validateNickName(CreateUserDto createUserDto) {
        if (userRepository.existsById(createUserDto.nickname())) {
            throw new RuntimeException("User already exists");
        }
    }


}
