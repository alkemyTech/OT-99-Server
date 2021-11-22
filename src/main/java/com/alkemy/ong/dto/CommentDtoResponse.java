package com.alkemy.ong.dto;

import com.alkemy.ong.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDtoResponse {
    private String username;
    private String newsname;
    private String content;

    public static CommentDtoResponse mapToDto(Comment comment){
        CommentDtoResponse commentDto = new CommentDtoResponse();
        commentDto.setContent(comment.getContent());
        comment.getNews().getName().length();
        commentDto.setNewsname(comment.getNews().getName());
        comment.getUser().getFirstName().length();
        commentDto.setUsername(comment.getUser().getFirstName());
        return commentDto;
    }
}
