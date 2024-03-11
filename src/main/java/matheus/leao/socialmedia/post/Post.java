package matheus.leao.socialmedia.post;

import jakarta.persistence.*;
import matheus.leao.socialmedia.post.dtos.PostDto;
import matheus.leao.socialmedia.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String content;
    @Column
    private Date createdAt;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private User user;
    @Column
    private Boolean isComment;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> comments;


    public Post() {
    }
    public Post(String content, User user) {
        this.content = content;
        this.user = user;
        this.createdAt = new Date();
        this.isComment = false;
        this.comments = new ArrayList<Post>();
    }

    public Post(String content, User user, Post commentedPost) {
        this.content = content;
        this.user = user;
        this.createdAt = new Date();
        this.isComment = true;
        this.comments = new ArrayList<Post>();
        commentedPost.addComment(this);
    }

    private void addComment(Post post) {
        this.comments.add(post);
    }

    public PostDto toPostDtoWithComents() {
        return new PostDto(id,content, this.user.toDto(), createdAt, comments.stream().map(Post::toPostDto).toList());
    }
    public PostDto toPostDto() {
        return new PostDto(id,content, this.user.toDto(), createdAt, new ArrayList<>());
    }



}
