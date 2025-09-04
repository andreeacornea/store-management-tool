package ro.project.store_management_tool.web.errorhandling;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.project.store_management_tool.exception.DbException;
import ro.project.store_management_tool.exception.NotFoundException;
import ro.project.store_management_tool.model.Error;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class,
            MissingRequestHeaderException.class,
            MissingServletRequestParameterException.class})
    public final ResponseEntity<Object> handleBadRq(Exception ex, HttpServletRequest request) {
        Error error = Error.builder()
                .errorCode("ERR_01")
                .errorMessage(
                    switch (ex) {
                            case MissingServletRequestParameterException e -> e.getMessage();
                            case HttpMessageNotReadableException e -> e.getMessage();
                            case MissingRequestHeaderException e -> e.getMessage();
                            case MethodArgumentNotValidException e ->
                                    e.getBindingResult().getFieldErrors().stream()
                                    .findFirst()
                                    .map(err-> err.getDefaultMessage())
                                    .orElse("Bad request");
                        case ConstraintViolationException e ->
                                e.getConstraintViolations().stream()
                                        .findFirst()
                                        .map(violation -> violation.getMessage())
                                        .orElse("Bad request");
                            default -> "Bad request";
                        })
                .build();

        MDC.put("data", error.toString());
        log.error("Bad request occurred");
        MDC.remove("data");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DbException.class)
    public final ResponseEntity<Object> handleDbException(DbException ex, HttpServletRequest request) {
        Error error = Error.builder()
                .errorCode("ERR_02")
                .errorMessage(ex.getMessage() != null ? ex.getMessage() : "Generic DB exception")
                .build();

        MDC.put("data", error.toString());
        log.error("DB exception occurred");
        MDC.remove("data");

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFound(NotFoundException ex, HttpServletRequest request) {
        Error error = Error.builder()
                .errorCode("ERR_03")
                .errorMessage(ex.getMessage() != null ? ex.getMessage() : "Not found products in DB")
                .build();

        MDC.put("data", error.toString());
        log.error("Product not found");
        MDC.remove("data");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleGenericException(Exception ex, HttpServletRequest request) {
        Error error = Error.builder()
                .errorCode("ERR_00")
                .errorMessage("Technical exception occurred.")
                .build();

        MDC.put("data", error.toString());
        log.error("Unexpected exception occurred");
        MDC.remove("data");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

