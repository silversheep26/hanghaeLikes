package com.sparta.hanghaejwt.repository;

import com.sparta.hanghaejwt.entity.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
    boolean existsByUserIdAndCommentId(Long id, Long comment_id);
    @Transactional
    void deleteByUserIdAndCommentId(Long id, Long comment_id);
    long countByCommentId(Long comment_id);
}
