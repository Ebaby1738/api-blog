package com.fashionApp.design.serviceImplementation;

import javax.servlet.http.HttpSession;

import com.fashionApp.design.dtos.CreateCommentDto;
import com.fashionApp.design.entity.ApiResponse;
import com.fashionApp.design.entity.Comment;
import com.fashionApp.design.entity.Post;
import com.fashionApp.design.entity.User;
import com.fashionApp.design.enums.Role;
import com.fashionApp.design.exceptions.NotNullException;
import com.fashionApp.design.exceptions.ResourceNotFoundException;
import com.fashionApp.design.repositories.CommentRepo;
import com.fashionApp.design.repositories.PostRepo;
import com.fashionApp.design.services.CommentService;
import com.fashionApp.design.util.LoggedInUser;
import com.fashionApp.design.util.ResponseManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;
    private final HttpSession httpSession;
    private final PostRepo postRepo;
    private final LoggedInUser loggedInUser;
    private final ResponseManager responseManager;

    @Override
    public ApiResponse<Comment> createComment(CreateCommentDto createCommentDto, Long postId) throws NotNullException, ResourceNotFoundException {
        if(createCommentDto.getComment().equals(""))
            throw new NotNullException("Please type in a comment");

        Comment comment = new Comment();
        BeanUtils.copyProperties(createCommentDto,comment);
        if(httpSession.getAttribute("userId") == null){
            User user = new User(Role.ANONYMOUS_USER);
            comment.setUser(user);
        } else {
            comment.setUser(loggedInUser.findLoggedInUser());
        }

        Post post = postRepo.findById(postId).get();
        if(post == null){
            throw new ResourceNotFoundException("This post is not available");
        }
        comment.setPost(post);

        commentRepo.save(comment);
        return responseManager.success(comment);
    }

    @Override
    public ApiResponse<List<Comment>> findAllPostComments(Long postId) throws ResourceNotFoundException {
        List<Comment> comments = commentRepo.findAllByPostId(postId);
        if(comments.size() == 0)
            throw new ResourceNotFoundException("No comments for this post yet");
        return responseManager.success(comments);
    }

    @Override
    public ApiResponse<Comment> updateComment(Long commentId, Comment newComment) throws NotNullException {
        if(newComment.equals(""))
            throw new NotNullException("Please type in a comment");
        Comment comment = commentRepo.findById(commentId).get();
        comment.setComment(newComment.getComment());
        commentRepo.save(comment);
        return responseManager.success(comment);
    }

    @Override
    public ApiResponse<String> deleteCommentById(Long commentId){
        commentRepo.deleteById(commentId);
        return responseManager.success("Deleted Successfully");
    }
}
