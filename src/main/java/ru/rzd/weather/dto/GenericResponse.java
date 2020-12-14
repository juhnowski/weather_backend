package ru.rzd.weather.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponse {

    public static final String DEFAULT_SUCCESS_MESSAGE = "request was completed successfully";

    private boolean status;
    private String message;
    private Object data;

    private GenericResponse(boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static GenericResponse of(Object data) {
        return new GenericResponse(true, DEFAULT_SUCCESS_MESSAGE, data);
    }
}

