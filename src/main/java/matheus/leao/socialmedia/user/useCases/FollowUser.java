package matheus.leao.socialmedia.user.useCases;

import matheus.leao.socialmedia.user.User;
import matheus.leao.socialmedia.user.UserRepository;
import matheus.leao.socialmedia.user.dtos.FollowUserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FollowUser {
    private final UserRepository userRepository;

    public FollowUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void execute(FollowUserDto followUserDto) {
         User follower = userRepository.findById(followUserDto.followerNick()).orElseThrow(() -> new RuntimeException("Follower not found"));
         User followed = userRepository.findById(followUserDto.followedNick()).orElseThrow(() -> new RuntimeException("Followed not found"));
         follower.followUser(followed);

         List<User> users = List.of(new User[]{follower, followed});
         userRepository.saveAll(users);
    }
}
