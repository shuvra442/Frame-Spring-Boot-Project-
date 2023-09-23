package in.ovaku.frame.framebackend.exceptions;
/*
 * Copyright (c) 2022 Ovaku.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This is a custom exception class.
 * It extends {@link RuntimeException}.
 *
 * @author sohan
 * @version 1.0
 * @since 24/06/22
 */
@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;
}
