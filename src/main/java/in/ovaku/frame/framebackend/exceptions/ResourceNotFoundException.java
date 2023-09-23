package in.ovaku.frame.framebackend.exceptions;
/*
 * Copyright (c) 2022 Ovaku.
 */

import org.springframework.http.HttpStatus;

/**
 * This is a custom exception class for resource not found.
 * It extends {@link ApiException}
 *
 * @author sohan
 * @version 1.0
 * @since 24/06/22
 */
public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
