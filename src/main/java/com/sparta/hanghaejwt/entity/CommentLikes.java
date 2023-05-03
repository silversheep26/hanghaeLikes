package com.sparta.hanghaejwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
//@Table(name = "comment_likes")
public class CommentLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference // 순환참조 방지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @JsonBackReference // 순환참조 방지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    public CommentLikes(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }
}
