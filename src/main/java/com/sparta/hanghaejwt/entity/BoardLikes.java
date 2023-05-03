package com.sparta.hanghaejwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "board_likes")
public class BoardLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference // 순환참조 방지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
//    @JsonManagedReference
    private Board board;

    @JsonBackReference // 순환참조 방지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
//    @JsonManagedReference
    private User user;

    public BoardLikes(Board board, User user) {
        this.board = board;
        this.user = user;
    }
}
