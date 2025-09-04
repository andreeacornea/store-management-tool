package ro.project.store_management_tool.exception;

import lombok.ToString;

@ToString
public class DbException extends RuntimeException{
    public DbException(String message) {
        super(message);
    }
}
