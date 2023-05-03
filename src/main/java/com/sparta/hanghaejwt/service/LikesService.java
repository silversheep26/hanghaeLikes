package com.sparta.hanghaejwt.service;

import com.sparta.hanghaejwt.entity.*;
import com.sparta.hanghaejwt.repository.BoardLikesRepository;
import com.sparta.hanghaejwt.repository.BoardRepository;
import com.sparta.hanghaejwt.repository.CommentLikesRepository;
import com.sparta.hanghaejwt.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final BoardLikesRepository boardLikesRepository;
    private final BoardRepository boardRepository;
    private final CommentLikesRepository commentLikesRepository;
    private final CommentRepository commentRepository;
    private final FindService findService;

    // 게시글 좋아요
    public Long boardLike(Long boardId, User user) {
        // 게시글 확인
        Board board = findService.findBoard(boardId);
        // 게시글에 좋아요가 있으면 삭제, 없으면 저장
        if (findService.findBoardLike(boardId, user)) {
            boardLikesRepository.deleteByUserIdAndBoardId(user.getId(), boardId);
        }  else {
            BoardLikes boardLikes = new BoardLikes(board, user);
            boardLikesRepository.save(boardLikes);
        }
        long boardLikeCount = boardLikesRepository.countByBoardId(boardId);
        board.boardLike(boardLikeCount);
        boardRepository.save(board);
        return boardLikeCount;
    }

    // 댓글 좋아요
    public Long commentLike(Long commentId, User user) {
        // 댓글 확인
        Comment comment = findService.findComment(commentId);
        // 댓글에 좋아요 있으면 삭제, 없으면 저장
        if (findService.findCommentLike(commentId, user)) {
            commentLikesRepository.deleteByUserIdAndCommentId(user.getId(), commentId);
        } else {
            CommentLikes commentLikes = new CommentLikes(comment, user);
            commentLikesRepository.save(commentLikes);
        }
        long commentLikeCount = commentLikesRepository.countByCommentId(commentId);
        comment.commentLike(commentLikeCount);
        commentRepository.save(comment);
        return commentLikeCount;
    }

}

