package com.fashionApp.design.controller;


import com.fashionApp.design.dtos.CreateCommentDto;
import com.fashionApp.design.entity.ApiResponse;
import com.fashionApp.design.entity.Comment;
import com.fashionApp.design.exceptions.NotNullException;
import com.fashionApp.design.exceptions.ResourceNotFoundException;
import com.fashionApp.design.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fashion-blog/posts")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<ApiResponse<Comment>> createComment(@PathVariable Long postId, @RequestBody CreateCommentDto createCommentDto) throws NotNullException, ResourceNotFoundException {
        ApiResponse<Comment> comment = commentService.createComment(createCommentDto, postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<ApiResponse<List<Comment>>> getAllCommentsForPost(@PathVariable Long postId) throws ResourceNotFoundException {
        ApiResponse<List<Comment>> comments = commentService.findAllPostComments(postId);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<Comment>> updateComment(@PathVariable Long commentId, @RequestBody Comment newComment) throws NotNullException {
        ApiResponse<Comment> comment = commentService.updateComment(commentId, newComment);
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<String>> deleteComment(@PathVariable Long commentId) {
        ApiResponse<String> response = commentService.deleteCommentById(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
