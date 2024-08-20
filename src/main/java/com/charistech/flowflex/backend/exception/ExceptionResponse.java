package com.charistech.flowflex.backend.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExceptionResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 6877490277661133451L;

    private HttpStatus status;
    private Integer error;
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;


    private ExceptionResponse() {
        this.timestamp = LocalDateTime.now();
    }

    ExceptionResponse(HttpStatus status, String message, Integer error) {
        this();
        this.status = status;
        this.message = message;
        this.error = error;
    }
}
