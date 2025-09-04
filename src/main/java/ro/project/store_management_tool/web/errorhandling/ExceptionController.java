package ro.project.store_management_tool.web.errorhandling;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.project.store_management_tool.exception.DbException;
import ro.project.store_management_tool.model.Error;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<Object> handleBadRq(Exception ex, HttpServletRequest request) {
        Error error = Error.builder()
                .errorCode("ERR_01")
                .errorMessage(ex.getMessage() != null ? ex.getMessage() : "Bad request")
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

