package edu.miu.cs.cs544.exception;

import edu.miu.cs.cs544.dto.response.ErrorResponseDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDTO> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatusCode status = ex.getStatusCode();
        String reason = ex.getReason();
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(reason, status);
        return ResponseEntity.status(status).body(errorResponse);
    }
}
