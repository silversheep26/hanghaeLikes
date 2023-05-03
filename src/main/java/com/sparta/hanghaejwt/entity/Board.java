package com.sparta.hanghaejwt.entity;
// 이 정보대로 데이터 베이스에 저장 된다.
// board : comment = 1 : n

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.hanghaejwt.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// @NoArgsConstructor : 기본 생성자를 생성
// @NoArgsConstructor(force = true) 사용시 기본 값으로 초기화
@Getter
@Entity
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @ColumnDefault("0")
    private long likeCount;

    // comment 와 양방향 관계, comment 가 주인
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

//    @JsonBackReference // 순환참조 방지
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<BoardLikes> boardLikesList = new ArrayList<>();

    @JsonIgnore
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Board(BoardRequestDto requestDto, User user){
        this.contents = requestDto.getContent();
        this.title = requestDto.getTitle();
        this.user = user;
    }

    public void update(BoardRequestDto requestDto) {
        this.contents = requestDto.getContent();
        this.title = requestDto.getTitle();
    }
    public void boardLike(long boardLikeCount) {
        this.likeCount = boardLikeCount;
    }
}
