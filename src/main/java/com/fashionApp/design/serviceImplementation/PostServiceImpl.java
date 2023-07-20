package com.fashionApp.design.serviceImplementation;

import com.fashionApp.design.dtos.CreatePostDto;
import com.fashionApp.design.entity.ApiResponse;
import com.fashionApp.design.entity.Post;
import com.fashionApp.design.enums.Role;
import com.fashionApp.design.exceptions.CustomAppException;
import com.fashionApp.design.exceptions.ResourceNotFoundException;
import com.fashionApp.design.repositories.PostRepo;
import com.fashionApp.design.services.PostService;
import com.fashionApp.design.util.LoggedInUser;
import com.fashionApp.design.util.ResponseManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;
    private final ResponseManager responseManager;
    private final HttpSession httpSession;
    private final LoggedInUser loggedInUser;

    @Override
    public ApiResponse<Post> createPost(CreatePostDto createPostDto) throws ResourceNotFoundException {
        validateAuthorization();
        validateCreatePostDto(createPostDto);

        Post post = new Post();
        BeanUtils.copyProperties(createPostDto, post);
        post.setUser(loggedInUser.findLoggedInUser());
        post.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        postRepo.save(post);
        return responseManager.success(post);
    }

    @Override
    public ApiResponse<Post> findPostById(Long postId) throws ResourceNotFoundException {
        validateAuthorization();
        validatePostExistence(postId);

        Post post = postRepo.findById(postId).get();
        return responseManager.success(post);
    }

    @Override
    public ApiResponse<Post> findPostByTitle(CreatePostDto createPostDto) throws ResourceNotFoundException {
        validateAuthorization();
        validatePostTitleExistence(createPostDto);

        Optional<Post> post = postRepo.findPostByPostTitle(createPostDto.getPostTitle());
        return responseManager.success(post.get());
    }





    @Override
    public ApiResponse<List<Post>> findAllPosts() throws ResourceNotFoundException {
        List<Post> allPosts = postRepo.findAll();
        if (allPosts.isEmpty()) {
            throw new ResourceNotFoundException("No posts yet");
        }
        return responseManager.success(allPosts);
    }

    @Override
    public ApiResponse<Post> updatePostById(Long postId, CreatePostDto createPostDto) throws ResourceNotFoundException {
        validateAuthorization();
        validatePostExistence(postId);

        Post post = postRepo.findById(postId).get();
        BeanUtils.copyProperties(createPostDto, post);
        post.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        postRepo.save(post);
        return responseManager.success(post);
    }

    @Override
    public void deletePostById(Long postId) throws ResourceNotFoundException {
        validateAuthorization();
        validatePostExistence(postId);

        postRepo.deleteById(postId);
    }

    private void validateAuthorization() throws RuntimeException {
        if (httpSession.getAttribute("userId") == null) {
            throw new ResourceNotFoundException("Please log in to carry out this operation");
        }
        if (loggedInUser.findLoggedInUser().getRole() != Role.BLOGGER) {
            throw new CustomAppException("You're not authorized to carry out this operation");
        }
    }

    private void validateCreatePostDto(CreatePostDto createPostDto) throws ResourceNotFoundException {
        if (createPostDto.getPostTitle().isEmpty() || createPostDto.getPostDescription().isEmpty()
                || createPostDto.getCategory() == null || createPostDto.getGender() == null) {
            throw new ResourceNotFoundException("You're missing one of the required inputs");
        }
    }

    private void validatePostExistence(Long postId) throws ResourceNotFoundException {
        if (!postRepo.existsById(postId)) {
            throw new ResourceNotFoundException("No such post");
        }
    }

    private void validatePostTitleExistence(CreatePostDto createPostDto) throws ResourceNotFoundException {
        if (!postRepo.existsPostByPostTitle(createPostDto.getPostTitle())) {
            throw new ResourceNotFoundException("No such post");
        }
    }
}
