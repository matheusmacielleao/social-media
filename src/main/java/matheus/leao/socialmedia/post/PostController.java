package matheus.leao.socialmedia.post;

import matheus.leao.socialmedia.post.dtos.CreatePostDto;
import matheus.leao.socialmedia.post.dtos.PostDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/{nickName}/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostDto createPost(@PathVariable("nickName") String authorNick, @RequestBody CreatePostDto body ){
        return this.postService.createPost(body.content(), authorNick);
    }

    @GetMapping
    public List<PostDto> getPostsByNickName(@PathVariable("nickName") String nickName) {
        return this.postService.getPostsByNickName(nickName);
    }

    @PostMapping("{postID}/comment")
    public PostDto commentPost(@PathVariable("nickName") String authorNick, @PathVariable("postID") String postID, @RequestBody CreatePostDto body){
        return this.postService.commentPost(body.content(), authorNick, postID);
    }
}
