package com.remproyectos.foro.infra.exceptions;

public class InvalidLoginException extends RuntimeException{
    public InvalidLoginException(String s){
        super(s);
    }

    public InvalidLoginException(RuntimeException exception) {
        super(exception);
    }
}
