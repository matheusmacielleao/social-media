package matheus.leao.socialmedia.post.useCases;

import matheus.leao.socialmedia.post.Post;
import matheus.leao.socialmedia.post.PostRepository;
import matheus.leao.socialmedia.post.dtos.PostDto;
import matheus.leao.socialmedia.user.User;
import matheus.leao.socialmedia.user.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CreatePost {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public  CreatePost (PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public PostDto execute(String content, String authorNick) {
        User author = userRepository.findById(authorNick).orElseThrow(() -> new RuntimeException("User not found"));
        Post newPost = new Post(content, author);
        postRepository.save(newPost);
        return newPost.toPostDto();
    }

    public PostDto  commentPost(String content, String authorNick, String postID) {
        User author = userRepository.findById(authorNick).orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(postID).orElseThrow(() -> new RuntimeException("Post not found"));
        Post comment =new Post(content, author, post);
        postRepository.save(post);
        postRepository.save(comment);
        return comment.toPostDto();
    }


}
