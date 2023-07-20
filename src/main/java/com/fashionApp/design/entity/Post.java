package com.fashionApp.design.entity;

import com.fashionApp.design.enums.Category;
import com.fashionApp.design.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "designT")
public class Post extends BaseEntity{


    private String postTitle;
    private String PostDescription;
    private Category category;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Timestamp updatedAt;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comment;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like> like;



}
