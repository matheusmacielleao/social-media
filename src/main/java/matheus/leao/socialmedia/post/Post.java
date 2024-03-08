package matheus.leao.socialmedia.post;

import jakarta.persistence.*;
import matheus.leao.socialmedia.user.User;

import java.util.Date;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(nullable = false)
    private String content;
    @Column
    private Date createdAt;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private User user;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> comments;

    public Post() {
    }
    public Post(String content, User user) {
        this.content = content;
        this.user = user;
        this.createdAt = new Date();
    }


}
