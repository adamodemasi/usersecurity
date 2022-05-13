package it.pegasoft.usersecurity.exception;

public class LibroNotFoundException extends RuntimeException{
    public LibroNotFoundException(String msg){
        super(msg);
    }
}