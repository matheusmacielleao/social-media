package matheus.leao.socialmedia.post.dtos;

import matheus.leao.socialmedia.user.dtos.UserDto;

import java.util.Date;
import java.util.List;

public record PostDto(String id, String content, UserDto author, Date date, List<PostDto> comments) {
}
