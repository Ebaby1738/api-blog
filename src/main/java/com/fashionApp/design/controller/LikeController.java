package com.fashionApp.design.controller;

import com.fashionApp.design.entity.ApiResponse;
import com.fashionApp.design.entity.Like;
import com.fashionApp.design.exceptions.ResourceNotFoundException;
import com.fashionApp.design.exceptions.UnauthorizedException;
import com.fashionApp.design.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fashion-blog")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/posts/{postId}/likes")
    public ResponseEntity<ApiResponse<Like>> likePost(@PathVariable Long postId) throws UnauthorizedException {
        ApiResponse<Like> newLike = likeService.likeAPost(postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLike);
    }

    @DeleteMapping("/posts/{postId}/likes")
    public ResponseEntity<ApiResponse<String>> unlikePost(@PathVariable Long postId) throws UnauthorizedException, ResourceNotFoundException {
        ApiResponse<String> response = likeService.unLikeAPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/comments/{commentId}/likes")
    public ResponseEntity<ApiResponse<Like>> likeComment(@PathVariable Long commentId) throws UnauthorizedException {
        ApiResponse<Like> newLike = likeService.likeAComment(commentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLike);
    }

    @DeleteMapping("/comments/{commentId}/likes")
    public ResponseEntity<ApiResponse<String>> unlikeComment(@PathVariable Long commentId) throws UnauthorizedException, ResourceNotFoundException {
        ApiResponse<String> response = likeService.unLikeAComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
