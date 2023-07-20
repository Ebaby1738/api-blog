package com.fashionApp.design.controller;

import com.fashionApp.design.dtos.UserDto;
import com.fashionApp.design.dtos.UserLoginDto;
import com.fashionApp.design.entity.ApiResponse;
import com.fashionApp.design.entity.User;
import com.fashionApp.design.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/blog")
public class UserController {

    public final UserService userService;
    private ApiResponse apiResponse;
    public final HttpSession httpSession;


    public UserController(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @PostMapping("/signup")
    public String createUser(@Valid @RequestBody UserDto userDto){
        userService.createUser(userDto);
        return "signUp was successful, go to login";
    }

    @PostMapping("/login")
    public User login(@Valid @RequestBody UserLoginDto userLoginDto){
        User user = userService.loginUser(userLoginDto.getEmail(), userLoginDto.getPassword());
        httpSession.setAttribute("userId", user.getId());
        return user;
    }

   @PostMapping("/logout")
    public ResponseEntity<String> logout(){
        userService.signOut();
        httpSession.removeAttribute("userId");
        httpSession.invalidate();
        return new ResponseEntity<>("user logged out successfully", HttpStatus.OK);
    }


}
