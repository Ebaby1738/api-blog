package com.fashionApp.design.serviceImplementation;

import javax.servlet.http.HttpSession;


import com.fashionApp.design.entity.*;
import com.fashionApp.design.enums.Role;
import com.fashionApp.design.exceptions.ResourceNotFoundException;
import com.fashionApp.design.exceptions.UnauthorizedException;
import com.fashionApp.design.repositories.CommentRepo;
import com.fashionApp.design.repositories.LikeRepo;
import com.fashionApp.design.repositories.PostRepo;
import com.fashionApp.design.services.LikeService;
import com.fashionApp.design.util.LoggedInUser;
import com.fashionApp.design.util.ResponseManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepo likeRepo;
    private final ResponseManager responseManager;
    private final HttpSession httpSession;
    private final LoggedInUser loggedInUser;
    private final PostRepo postRepo;
    private final CommentRepo commentRepo;

    @Override
    public ApiResponse<Like> likeAPost(Long postId) throws UnauthorizedException {
        List<Like> likes = likeRepo.findLikesByPostId(postId);

        for(int i = 0; i < likes.size(); i++){
            if(likes.get(i).getUser() == loggedInUser.findLoggedInUser()){
                throw new UnauthorizedException("You can't like this post more than once");
            }
        }

        Like like = new Like();

        if(httpSession.getAttribute("userId") == null){
            User user = new User(Role.ANONYMOUS_USER);
            like.setUser(user);
        }
        else {
            like.setUser(loggedInUser.findLoggedInUser());
        }

        Post post = postRepo.findById(postId).get();
        like.setPost(post);

        likeRepo.save(like);
        return responseManager.success(like);
    }

    @Override
    public ApiResponse<String> unLikeAPost(Long postId) throws UnauthorizedException, ResourceNotFoundException {
        if(httpSession.getAttribute("userId") == null)
            throw new UnauthorizedException("Please log in to unlike a post");

        Like userLike =likeRepo.findLikeByPostIdAndUser(postId,loggedInUser.findLoggedInUser());
        if(userLike == null)
            throw new ResourceNotFoundException("You didn't like this post");
        likeRepo.deleteById(userLike.getId());
        return responseManager.success("Unliked Successfully");
    }

    @Override
    public ApiResponse<Like> likeAComment(Long commentId) throws UnauthorizedException {
        if(httpSession.getAttribute("userId") == null)
            throw new UnauthorizedException("Please log in to like a comment");

        List<Like> likes = likeRepo.findLikesByCommentId(commentId);
        for(int i = 0; i < likes.size(); i++){
            if(likes.get(i).getUser() == loggedInUser.findLoggedInUser()){
                throw new UnauthorizedException("You can't like this comment more than once");
            }
        }

        Like like = new Like();
        like.setUser(loggedInUser.findLoggedInUser());

        Comment comment = commentRepo.findById(commentId).get();
        like.setComment(comment);

        likeRepo.save(like);
        return responseManager.success(like);
    }

    @Override
    public ApiResponse<String> unLikeAComment(Long commentId) throws UnauthorizedException, ResourceNotFoundException {
        if(httpSession.getAttribute("userId") == null)
            throw new UnauthorizedException("Please log in to unlike a comment");

        Like userLike = likeRepo.findLikeByCommentIdAndUser(commentId,loggedInUser.findLoggedInUser());
        if(userLike == null)
            throw new ResourceNotFoundException("You didn't like this comment");

        likeRepo.deleteById(userLike.getId());
        return responseManager.success("Comment unliked successfully");
    }

}
