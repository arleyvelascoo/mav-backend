package com.example.mavbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for errors management
 */

@Getter
@AllArgsConstructor
public class ErrorDTO implements Serializable {
    private static final long serialVersionUID = 5948272356222772300L;

    @JsonProperty("status_code")
    private final Integer statusCode;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("type")
    private final String errorType;

    @JsonProperty("uri")
    private final String uriRequested;
}
