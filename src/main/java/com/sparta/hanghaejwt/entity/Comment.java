package com.sparta.hanghaejwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.hanghaejwt.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
//@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Board board;  // fk

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<CommentLikes> commentLikesList = new ArrayList<>();

//    @JoinColumn(name = "commentLikes_id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private CommentLikes commentLikes;

    @Column(nullable = false)
    @ColumnDefault("0")
    private long likeCount;

    @Column(nullable = false)
    private String text;

    public Comment(Board board, User user, CommentRequestDto requestDto) {
        this.board = board;
        this.user = user;
        this.text = requestDto.getText();
    }

    public void update(CommentRequestDto commentRequestDto){
        this.text = commentRequestDto.getText();
    }

    public void commentLike(long commentLikeCount) {
        this.likeCount = commentLikeCount;
    }
}
