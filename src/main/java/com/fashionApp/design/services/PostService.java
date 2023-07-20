package com.fashionApp.design.services;

import com.fashionApp.design.dtos.CreatePostDto;
import com.fashionApp.design.entity.ApiResponse;
import com.fashionApp.design.entity.Post;
import com.fashionApp.design.exceptions.ResourceNotFoundException;

import java.util.List;

public interface PostService {

    ApiResponse<Post> createPost(CreatePostDto createPostDto) throws ResourceNotFoundException;
    ApiResponse<Post> findPostById(Long postId) throws ResourceNotFoundException;

    ApiResponse<Post> findPostByTitle(CreatePostDto createPostDto) throws ResourceNotFoundException;

    ApiResponse<List<Post>> findAllPosts() throws ResourceNotFoundException;
    ApiResponse<Post> updatePostById(Long postId, CreatePostDto createPostDto) throws ResourceNotFoundException;
    void deletePostById(Long postId) throws ResourceNotFoundException;
}
