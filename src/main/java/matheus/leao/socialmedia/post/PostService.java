package matheus.leao.socialmedia.post;

import matheus.leao.socialmedia.post.dtos.PostDto;
import matheus.leao.socialmedia.post.useCases.CreatePost;
import matheus.leao.socialmedia.user.User;
import matheus.leao.socialmedia.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private  final CreatePost createPost;
    private  final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(CreatePost createPost, PostRepository postRepository, UserRepository userRepository) {
        this.createPost = createPost;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public PostDto createPost(String content, String authorNick) {
       return createPost.execute(content, authorNick);
    }

    public PostDto commentPost(String content, String authorNick, String postID) {
        return createPost.commentPost(content, authorNick, postID);
    }

    public List<PostDto> getPostsByNickName(String nickName) {
        User user = userRepository.findById(nickName).orElseThrow(() -> new RuntimeException("User not found"));

        return user.getPosts().stream().map(Post::toPostDtoWithComents).toList();

    }
}
