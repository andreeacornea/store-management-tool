package ro.project.store_management_tool.exception;
import lombok.ToString;

@ToString
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}