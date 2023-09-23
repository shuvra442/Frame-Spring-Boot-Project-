package in.ovaku.frame.framebackend.configurations;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.responses.ApiResponseDto;
import in.ovaku.frame.framebackend.exceptions.ApiException;
import in.ovaku.frame.framebackend.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is a configuration class for Exception Handler.
 *
 * @author Sohan
 * @version 1.0
 * @since 25/06/2022
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This method handle any exceptions of the type {@link ApiException}
     *
     * @param exception - custom Exception to be thrown
     * @return response
     */
    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<Object> handleApiException(ApiException exception) {
        return new ApiResponseDto().generateResponse(exception.getHttpStatus(), null, exception.getMessage());
    }

    /**
     * This method handle any exceptions of the type {@link ValidationException}
     *
     * @param exception - custom Exception to be thrown
     * @return response
     */
    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException exception) {
        return new ApiResponseDto().generateResponse(HttpStatus.BAD_REQUEST, null, exception.getMessage());
    }
}
