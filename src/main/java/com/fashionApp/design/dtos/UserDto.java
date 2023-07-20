package com.fashionApp.design.dtos;

import com.fashionApp.design.enums.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String gender;

    private Role role;


}
