package com.sparta.hanghaejwt.dto;

import com.sparta.hanghaejwt.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto implements BoardAndComment{
    private Long comment_id;
    private String text;
    private String writer;
    private long likeCount;

//    private Long board_id;

    public CommentResponseDto(Comment comment){
        this.comment_id = comment.getId();
        this.writer = comment.getUser().getUsername();
        this.text = comment.getText();
        this.likeCount = comment.getLikeCount();
    }
}


// comment id, content , createdAt, modifiedAt, username