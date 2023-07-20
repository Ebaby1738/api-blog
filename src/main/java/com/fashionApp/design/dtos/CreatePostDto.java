package com.fashionApp.design.dtos;

import com.fashionApp.design.enums.Category;
import com.fashionApp.design.enums.Gender;
import lombok.Data;

@Data
public class CreatePostDto {
        private String postTitle;
        private String postDescription;
        private Category category;
        private Gender gender;
}
