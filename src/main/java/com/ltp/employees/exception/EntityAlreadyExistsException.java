package com.ltp.employees.exception;

public class EntityAlreadyExistsException extends RuntimeException { 

    public EntityAlreadyExistsException(String username, Class<?> entity) {
            super("The " + entity.getSimpleName().toLowerCase() + " with username '" + username + "' already exists in our records");
    } 

}