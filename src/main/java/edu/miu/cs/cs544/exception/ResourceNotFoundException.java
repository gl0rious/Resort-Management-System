package edu.miu.cs.cs544.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {

    public ResourceNotFoundException(Class<?> clazz, int id) {
        super(HttpStatus.NOT_FOUND, String.format("%s with id: %d Not Found", clazz.getSimpleName() , id));
    }

}
