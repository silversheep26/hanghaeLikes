package com.sparta.hanghaejwt.dto;

import com.sparta.hanghaejwt.entity.Board;
import com.sparta.hanghaejwt.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto implements BoardAndComment{
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private String username;
    private long likeCount;

    private List<CommentResponseDto> commentResponseDto = new ArrayList<>();

    public BoardResponseDto(Board board) {
        this.username = board.getUser().getUsername();
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContents();
        this.likeCount = board.getLikeCount();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.commentResponseDto = board.getCommentList().stream()
                .map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
