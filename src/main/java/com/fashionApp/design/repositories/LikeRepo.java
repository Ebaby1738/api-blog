package com.fashionApp.design.repositories;


import com.fashionApp.design.entity.Like;
import com.fashionApp.design.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepo extends JpaRepository<Like,Long> {
    List<Like> findLikesByPostId(Long postId);
    Like findLikeByPostIdAndUser(Long postId, User user);
    List<Like> findLikesByCommentId(Long commentId);
    Like findLikeByCommentIdAndUser(Long commentId, User user);

}
