package com.fashionApp.design.repositories;

import com.fashionApp.design.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepo extends JpaRepository<Post, Long> {

    Optional<Post> findPostByPostTitle(String postTitle);

    boolean existsPostByPostTitle(String postTitle);
//

}
