package com.fashionApp.design.services;

import com.fashionApp.design.dtos.UserDto;
import com.fashionApp.design.entity.User;

public interface UserService {
    void createUser(UserDto userDto);

    User loginUser(String email, String password);

    void signOut();
}
