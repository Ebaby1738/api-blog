package com.fashionApp.design.services;

import com.fashionApp.design.dtos.CreateCommentDto;
import com.fashionApp.design.entity.ApiResponse;
import com.fashionApp.design.entity.Comment;
import com.fashionApp.design.exceptions.NotNullException;
import com.fashionApp.design.exceptions.ResourceNotFoundException;

import java.util.List;

public interface CommentService {
    ApiResponse<Comment> createComment(CreateCommentDto createCommentDto, Long postId) throws NotNullException, ResourceNotFoundException;

    ApiResponse<List<Comment>> findAllPostComments(Long postId) throws ResourceNotFoundException;

    ApiResponse<Comment> updateComment(Long commentId, Comment newComment) throws NotNullException;

    ApiResponse<String> deleteCommentById(Long commentId);
}
