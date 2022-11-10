package com.git.gestoracademico.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MessageExceptionHandler {

    private LocalDateTime timestamp;
    private Integer status;
    private String message;
}
