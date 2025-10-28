package com.example.runestore.exceptions.notfound;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(){
        super("Nenhum usuário encontrado!");
    }
}
