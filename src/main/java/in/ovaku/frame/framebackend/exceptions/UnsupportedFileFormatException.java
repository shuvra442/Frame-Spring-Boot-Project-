package in.ovaku.frame.framebackend.exceptions;
/*
 * Copyright (c) 2023 Ovaku.
 */

import org.springframework.http.HttpStatus;

/**
 * Custom class for unsupported file formats.
 * It extends {@link ApiException}
 *
 * @author Sunny Batabyal
 * @version 1.0
 * @since 25/02/23
 */
public class UnsupportedFileFormatException extends ApiException {

    public UnsupportedFileFormatException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
