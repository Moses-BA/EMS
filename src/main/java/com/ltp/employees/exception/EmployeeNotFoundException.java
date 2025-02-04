package com.ltp.employees.exception;

public class EmployeeNotFoundException extends RuntimeException { 

    public EmployeeNotFoundException(String id) {
        super("The id '" + id + "' does not exist in our records");
    }
    
}
