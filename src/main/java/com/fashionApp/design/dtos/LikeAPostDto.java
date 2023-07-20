package com.fashionApp.design.dtos;


import com.fashionApp.design.entity.Post;
import com.fashionApp.design.entity.User;
import lombok.Data;

@Data
public class LikeAPostDto {
    private Long noOfLikes = 0L;
    private User user;
    private Post post;
}
