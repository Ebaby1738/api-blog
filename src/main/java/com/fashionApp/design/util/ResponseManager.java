package com.fashionApp.design.util;

import com.fashionApp.design.entity.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ResponseManager<T> {
    public ApiResponse<T> success(T data) {

        return new ApiResponse<T>((T) "your Request was Successful", true, data);
    }

    /*public ApiResponse<T> failure(T data){
        return new ApiResponse<T>((T) "your request was not successful", false, data);
    }*/

}
