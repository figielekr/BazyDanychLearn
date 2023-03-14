package com.bazydanychtest.user.tables;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Integer> {
    boolean existsByCommentIDAndUsername(int id, String name);
    @Transactional
    void deleteByCommentIDAndUsername(int id, String name);
}
