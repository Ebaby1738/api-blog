package com.fashionApp.design.serviceImplementation;

import com.fashionApp.design.dtos.UserDto;
import com.fashionApp.design.entity.ApiResponse;
import com.fashionApp.design.entity.User;
import com.fashionApp.design.exceptions.ResourceAlreadyExistException;
import com.fashionApp.design.repositories.UserRepo;
import com.fashionApp.design.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private HttpSession httpSession;



    @Override
    public void createUser(UserDto userDto){
        Optional<User> checkUser = userRepo.findByEmail(userDto.getEmail());
        if(checkUser.isPresent()){
            throw new ResourceAlreadyExistException("The email already exist, please use a different email");
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setGender(userDto.getGender());
        user.setRole(userDto.getRole());
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        userRepo.save(user);
    }

    @Override
    public User loginUser(String email, String password){
        User checkUser = userRepo.findByEmailAndPassword(email, password).orElseThrow(()->new RuntimeException("invalid email or password"));
        return checkUser;
    }

    @Override
    public void signOut(){
        httpSession.removeAttribute("userId");
        httpSession.invalidate();
}



}
