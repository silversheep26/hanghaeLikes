package com.sparta.hanghaejwt.service;

import com.sparta.hanghaejwt.entity.Board;
import com.sparta.hanghaejwt.entity.Comment;
import com.sparta.hanghaejwt.entity.User;
import com.sparta.hanghaejwt.repository.BoardLikesRepository;
import com.sparta.hanghaejwt.repository.BoardRepository;
import com.sparta.hanghaejwt.repository.CommentLikesRepository;
import com.sparta.hanghaejwt.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final BoardLikesRepository boardLikesRepository;
    private final CommentLikesRepository commentLikesRepository;

    //board_id를 이용해 해당 board 찾기
    public Board findBoard(Long board_id) {
        Board board = boardRepository.findById(board_id).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
       return board;
    }

    // 댓글 찾기
    public Comment findComment(Long comment_id){
        Comment comment = commentRepository.findById(comment_id).orElseThrow(
                () -> new IllegalArgumentException("선택한 댓글이 없습니다")
        );
        return comment;
    }

    // board like 있는지 확인
    public boolean findBoardLike(Long board_id, User user) {
        boolean like = boardLikesRepository.existsByUserIdAndBoardId(user.getId(), board_id);
        return like;
    }


    // comment Like 있는지 확인
    public boolean findCommentLike(Long comment_id, User user) {
        // 좋아요 존재 확인 ( 있으면 true, 없으면 false )
        boolean like = commentLikesRepository.existsByUserIdAndCommentId(user.getId(), comment_id);
        return like;
    }
}

