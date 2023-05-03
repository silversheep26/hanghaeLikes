package com.sparta.hanghaejwt.repository;

import com.sparta.hanghaejwt.entity.BoardLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BoardLikesRepository extends JpaRepository<BoardLikes, Long> {
    boolean existsByUserIdAndBoardId(Long userId, Long boardId);
    @Transactional
    void deleteByUserIdAndBoardId(Long userId, Long boardId);
    long countByBoardId(Long boardId);
}
