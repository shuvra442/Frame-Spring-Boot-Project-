package in.ovaku.frame.framebackend.exceptions;
/*
 * Copyright (c) 2022 Ovaku.
 */

import org.springframework.http.HttpStatus;

/**
 * This is a custom exception class for validation.
 * It extends {@link ApiException}.
 * It validates user inputs
 *
 * @author sohan
 * @version 1.0
 * @since 24/06/22
 */
public class ValidationException extends ApiException {

    public ValidationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
