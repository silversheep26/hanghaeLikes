package com.sparta.hanghaejwt.controller;

import com.sparta.hanghaejwt.security.UserDetailsImpl;
import com.sparta.hanghaejwt.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {
    private final LikesService likesService;

    // 게시글 좋아요
    @PostMapping("/boardlikes/{board_id}")
    public Long boardLikes(@PathVariable Long board_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likesService.boardLike(board_id, userDetails.getUser());
    }

    // 댓글 좋아요
    @PostMapping("/commentlikes/{comment_id}")
    public Long commentLikes(@PathVariable Long comment_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likesService.commentLike(comment_id, userDetails.getUser());
    }
}
