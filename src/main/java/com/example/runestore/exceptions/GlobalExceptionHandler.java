package com.example.runestore.exceptions;

import com.example.runestore.VOs.ErrorResponseVO;
import com.example.runestore.exceptions.invalid.InvalidUserException;
import com.example.runestore.exceptions.notfound.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseVO> handleBadCredentialsException(BadCredentialsException badCredentialsException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseVO("Usuário ou senha inválidos!"));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseVO> handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseVO("Usuário não encontrado!"));
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ErrorResponseVO> handleInvalidUserException(InvalidUserException invalidUserException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseVO(invalidUserException.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseVO> handleUserNotFoundException(UserNotFoundException userNotFoundException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseVO(userNotFoundException.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseVO> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseVO("Informações inválidas!"));
    }
}
