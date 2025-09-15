package org.example.orm_final.bo.exception;

public class DuplicateID extends RuntimeException {
    public DuplicateID(String massaege){
        super(massaege);
    }
}
