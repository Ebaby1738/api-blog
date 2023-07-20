package com.fashionApp.design.services;

import com.fashionApp.design.entity.ApiResponse;
import com.fashionApp.design.entity.Like;
import com.fashionApp.design.exceptions.ResourceNotFoundException;
import com.fashionApp.design.exceptions.UnauthorizedException;

public interface LikeService {
    ApiResponse<Like> likeAPost(Long postId) throws UnauthorizedException;

    ApiResponse<String> unLikeAPost(Long postId) throws UnauthorizedException, ResourceNotFoundException;

    ApiResponse<Like> likeAComment(Long commentId) throws UnauthorizedException;

    ApiResponse<String> unLikeAComment(Long commentId) throws UnauthorizedException, ResourceNotFoundException;
}
