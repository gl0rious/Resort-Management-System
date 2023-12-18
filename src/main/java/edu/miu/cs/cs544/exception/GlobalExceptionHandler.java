package edu.miu.cs.cs544.exception;

import edu.miu.cs.cs544.dto.response.ErrorResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDTO<String>> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatusCode status = ex.getStatusCode();
        String reason = ex.getReason();
        ErrorResponseDTO<String> errorResponse = new ErrorResponseDTO<>(reason, status);
        LOGGER.debug("message: {}, reason: {}", ex.getMessage(), ex.getReason());
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO<Map<String, String>>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        HttpStatusCode status = ex.getStatusCode();
        ErrorResponseDTO<Map<String, String>> errorResponse = new ErrorResponseDTO<>(errorMap, status);
        LOGGER.debug(ex.getMessage());
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO<String>> handleJsonParseError(HttpMessageNotReadableException ex) {
        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        ErrorResponseDTO<String> errorResponse = new ErrorResponseDTO<>(status.toString(), status);
        LOGGER.debug(ex.getMessage());
        return ResponseEntity.status(status).body(errorResponse);
    }
}
