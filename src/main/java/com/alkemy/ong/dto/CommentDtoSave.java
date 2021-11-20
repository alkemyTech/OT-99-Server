package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentDtoSave {
    @NotNull
    private Long userId;
    @NotNull
    private Long postId;
    @NotEmpty
    private String body;
}
