package com.fashionApp.design.controller;

import com.fashionApp.design.dtos.CreatePostDto;
import com.fashionApp.design.entity.ApiResponse;
import com.fashionApp.design.entity.Post;
import com.fashionApp.design.exceptions.CustomAppException;
import com.fashionApp.design.exceptions.ResourceNotFoundException;
import com.fashionApp.design.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<ApiResponse<Post>> createPost(@RequestBody @Valid CreatePostDto createPostDto) throws CustomAppException {
        ApiResponse<Post> post = postService.createPost(createPostDto);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<Post>> getPostById(@PathVariable Long postId) throws CustomAppException {
        ApiResponse<Post> post = postService.findPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/postTitle")
    public ResponseEntity<ApiResponse<Post>> getPostByTitle(@RequestBody CreatePostDto createPostDto) throws CustomAppException {
        ApiResponse<Post> post = postService.findPostByTitle(createPostDto);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/allPost")
    public ResponseEntity<ApiResponse<List<Post>>> getAllPosts() throws CustomAppException{
        ApiResponse<List<Post>> allPosts = postService.findAllPosts();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse<Post>> updatePost(@PathVariable Long postId, @RequestBody @Valid CreatePostDto createPostDto) throws ResourceNotFoundException {
        ApiResponse<Post> post = postService.updatePostById(postId, createPostDto);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) throws ChangeSetPersister.NotFoundException {
        postService.deletePostById(postId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}

