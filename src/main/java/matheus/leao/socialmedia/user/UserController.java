package matheus.leao.socialmedia.user;

import matheus.leao.socialmedia.user.UserService;
import matheus.leao.socialmedia.user.dtos.CreateUserDto;
import matheus.leao.socialmedia.user.dtos.FollowUserDto;
import matheus.leao.socialmedia.user.dtos.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto createUser(@RequestBody CreateUserDto createUserDto) {
        return this.userService.createUser.execute(createUserDto);
    }

    @PostMapping("{nickName}/follow")
    public void followUser(@RequestBody  FollowUserDto followUserDto, @PathVariable("nickName") String followerNick) {
        this.userService.followUser.execute(followUserDto);
    }

    @GetMapping("{nickName}")
    public UserDto getUser(@PathVariable("nickName") String nickName) {
        return this.userService.getUser.byNickName(nickName);
    }

    @GetMapping("{nickName}/followers")
    public List<UserDto> getFollowers(@PathVariable("nickName") String nickName) {
        return this.userService.getUser.followersByNickName(nickName);
    }
}
