package org.example.orm_final.bo.exception;

public class NotFound extends RuntimeException{
    public NotFound(String massage){
        super(massage);
    }
}
