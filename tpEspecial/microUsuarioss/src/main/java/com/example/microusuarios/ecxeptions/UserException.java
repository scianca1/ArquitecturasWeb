package com.example.microusuarios.ecxeptions;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private final EnumUserException code;
    private final String message;

    public UserException( EnumUserException code, String message ){
        this.code = code;
        this.message = message;
    }
}
