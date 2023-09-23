package in.ovaku.frame.framebackend.dtos.responses;
/*
 * Copyright (c) 2022 Ovaku.
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a dto class for custom response handler.
 * It is used to generate response.
 *
 * @author Sohan
 * @version 1.0
 * @since 27/05/2022
 */
public class ApiResponseDto {
    public ResponseEntity<Object> generateResponse(HttpStatus status, Object body, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("payload", body);
        map.put("message", message);
        return new ResponseEntity<>(map, status);
    }
}
