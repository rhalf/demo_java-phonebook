package com.rhalf.phonebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


import java.net.URI;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorHandler {
    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private URI path;
}
