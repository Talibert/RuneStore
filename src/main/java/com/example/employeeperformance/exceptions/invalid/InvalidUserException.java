package com.example.employeeperformance.exceptions.invalid;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(){
        super("Login de usuário inválido!");
    }

    public InvalidUserException(String message){
        super(message);
    }
}
