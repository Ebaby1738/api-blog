package com.fashionApp.design.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ErrorResponse {
    private String Message;
    private HttpStatus httpStatus;
    private String debugMessage;
}
