package com.sparta.hanghaejwt.service;

import com.sparta.hanghaejwt.dto.CommentRequestDto;
import com.sparta.hanghaejwt.dto.CommentResponseDto;
import com.sparta.hanghaejwt.dto.MessageStatusResponseDto;
import com.sparta.hanghaejwt.entity.Board;
import com.sparta.hanghaejwt.entity.Comment;
import com.sparta.hanghaejwt.entity.User;
import com.sparta.hanghaejwt.entity.UserRoleEnum;
import com.sparta.hanghaejwt.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final LikesService likesService;
    private final FindService findService;

    //댓글 생성
    public CommentResponseDto createComment(Long board_id, CommentRequestDto commentRequestDto, User user) {
//        선택한 게시글의 DB 저장 유무를 확인하기
        Board board = findService.findBoard(board_id);

        //댓글 저장
        Comment comment = new Comment(board, user, commentRequestDto);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    //댓글 수정 :)
    public CommentResponseDto updateComment(Long comment_id, CommentRequestDto commentRequestDto, User user) {
        // 댓글 존재여부 확인
        Comment comment = findService.findComment(comment_id);

        // 관리자인지 일반 유저인지 확인하기
        if (user.getRole() == UserRoleEnum.USER) {  // 일반 유저인 경우
            if (user.getUsername().equals(comment.getUser().getUsername())) {  // username 확인
                comment.update(commentRequestDto);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 댓글의 작성자가 아닙니다.", new IllegalArgumentException());
            }
        } else {  // admin 인 경우
            comment.update(commentRequestDto);
        }
        return new CommentResponseDto(comment);
    }


    //댓글 삭제
    public MessageStatusResponseDto deleteComment(Long comment_id, User user) {
        // 댓글 존재여부 확인
        Comment comment = findService.findComment(comment_id);
        if (user.getRole() == UserRoleEnum.USER) {
            if (user.getUsername().equals(comment.getUser().getUsername())) {
                commentRepository.delete(comment);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username 이 다릅니다.");
            }
        } else {
            commentRepository.delete(comment);
        }
        return new MessageStatusResponseDto("성공적으로 삭제되었습니다.", HttpStatus.OK);
    }
}
