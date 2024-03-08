package matheus.leao.socialmedia.user.useCases;

import matheus.leao.socialmedia.user.User;
import matheus.leao.socialmedia.user.UserRepository;
import matheus.leao.socialmedia.user.dtos.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetUser {
    private UserRepository userRepository;
    public GetUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto byNickName(String nickname) {
        User user = userRepository.findById(nickname).orElseThrow(() -> new RuntimeException("User not found"));
        return user.toDto();
    }

    public List<UserDto> followersByNickName(String nickname) {
        User user = userRepository.findById(nickname).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFollowers().stream().map(User::toDto).collect(Collectors.toList());
    }

}
