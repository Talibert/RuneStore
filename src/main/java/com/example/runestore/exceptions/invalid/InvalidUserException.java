package com.example.runestore.exceptions.invalid;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(){
        super("Login de usuário inválido!");
    }

    public InvalidUserException(String message){
        super(message);
    }
}
