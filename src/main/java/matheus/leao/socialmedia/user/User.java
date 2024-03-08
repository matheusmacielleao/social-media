package matheus.leao.socialmedia.user;

import jakarta.persistence.*;
import matheus.leao.socialmedia.post.Post;
import matheus.leao.socialmedia.user.dtos.UserDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String nickname;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "followers",
            joinColumns = {@JoinColumn(name = "user_nickname", referencedColumnName = "nickname")}
    )
    private List<User> followers;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "followers_request",
            joinColumns = {@JoinColumn(name = "user_nickname", referencedColumnName = "nickname")}
    )
    private List<User> followers_request;

    public User() {
    }
    public User(String nickname, String name, String email, String password) {
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.password = password;
        this.followers= new ArrayList<User>();
    }
    public UserDto toDto() {
        return new UserDto(nickname, name);
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void followUser(User userToFollow) {
        if (followers_request.contains(userToFollow)){
            followers_request.remove(userToFollow);
            followers.add(userToFollow);
            userToFollow.followers.add(this);
            return;
        }
        userToFollow.addFollowRequest(this);
    }

    public List<User> getFollowRequest() {
        return followers_request;
    }

    private void addFollowRequest(User follower) {
        followers_request.add(follower);
    }

    public void removeFollower(User follower) {
        followers.remove(follower);
    }


}
