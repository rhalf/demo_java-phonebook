package com.rhalf.phonebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URI;
import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorHandler {
    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private URI path;
}
