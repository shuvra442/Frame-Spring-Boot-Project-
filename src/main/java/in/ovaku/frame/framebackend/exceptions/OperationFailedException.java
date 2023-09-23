package in.ovaku.frame.framebackend.exceptions;
/*
 * Copyright (c) 2022 Ovaku.
 */

import org.springframework.http.HttpStatus;

/**
 * This is a custom exception class for operation failed.
 * It extends {@link ApiException}
 *
 * @author sohan
 * @version 1.0
 * @since 24/06/22
 */
public class OperationFailedException extends ApiException {

    public OperationFailedException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
