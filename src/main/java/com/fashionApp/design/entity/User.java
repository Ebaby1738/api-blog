package com.fashionApp.design.entity;

import com.fashionApp.design.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "userT")
public class User extends BaseEntity {


    private String firstName;
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String gender;




    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private List<Post> post = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private List<Comment> comment = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private List<Like> like;

    public User(Role role) {
        this.role = role;
    }


    //ONE USER TO MANY DESIGN
    //ONE USER TO MANY COMMENT
    //ONE USER TO ONE LIKE
    //




}
