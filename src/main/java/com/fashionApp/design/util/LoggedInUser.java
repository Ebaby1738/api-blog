package com.fashionApp.design.util;

import com.fashionApp.design.entity.User;
import com.fashionApp.design.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@AllArgsConstructor
public class LoggedInUser {
    private final HttpSession httpSession;
    private final UserRepo userRepo;

    public User findLoggedInUser() {
        Long id = (Long) httpSession.getAttribute("userId");
        return userRepo.findById(id).get();
    }
}
