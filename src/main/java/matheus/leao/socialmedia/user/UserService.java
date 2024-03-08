package matheus.leao.socialmedia.user;

import matheus.leao.socialmedia.user.dtos.UserDto;
import matheus.leao.socialmedia.user.useCases.CreateUser;
import matheus.leao.socialmedia.user.useCases.FollowUser;
import matheus.leao.socialmedia.user.useCases.GetUser;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public CreateUser createUser;
    public FollowUser followUser;
    public GetUser getUser;

    public UserService(CreateUser createUser, FollowUser followUser, GetUser getUser) {
        this.createUser = createUser;
        this.followUser = followUser;
        this.getUser = getUser;
    }

}
