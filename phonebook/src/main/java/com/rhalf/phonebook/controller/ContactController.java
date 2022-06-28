package com.rhalf.phonebook.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.rhalf.phonebook.model.Contact;
import com.rhalf.phonebook.model.ErrorHandler;
import com.rhalf.phonebook.service.ContactService;
import com.sun.jndi.toolkit.url.Uri;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public ResponseEntity getContacts() {
        return ResponseEntity.ok().body(contactService.fetchAll());
    }

    @PostMapping("/contact")
    public ResponseEntity saveContact(@RequestBody Contact contact, HttpServletRequest request) {
        URI uri = URI.create(request.getRequestURI());
        try {
            return ResponseEntity.created(uri).body(contactService.save(contact));
        } catch (Exception exception) {

            ErrorHandler errorHandler = new ErrorHandler(
                    new Date(),
                    HttpStatus.FORBIDDEN.value(),
                    HttpStatus.FORBIDDEN.name(),
                    exception.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity
                    .status(errorHandler.getStatus())
                    .body(errorHandler);
        }
    }

    @PutMapping("/contact/{id}")
    public ResponseEntity updateContact(@RequestBody Contact contact, @PathVariable Long id, HttpServletRequest request) {
        URI uri = URI.create(request.getRequestURI());
        try {
            return ResponseEntity.ok().body(contactService.update(id, contact));
        } catch (Exception exception) {
            ErrorHandler errorHandler = new ErrorHandler(
                    new Date(),
                    HttpStatus.FORBIDDEN.value(),
                    HttpStatus.FORBIDDEN.name(),
                    exception.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity
                    .status(errorHandler.getStatus())
                    .body(errorHandler);
        }
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity getContact(@PathVariable Long id, HttpServletRequest request) {
        URI uri = URI.create(request.getRequestURI());
        try {
            return ResponseEntity.ok().body(contactService.findById(id));
        } catch (Exception exception) {
            ErrorHandler errorHandler = new ErrorHandler(
                    new Date(),
                    HttpStatus.FORBIDDEN.value(),
                    HttpStatus.FORBIDDEN.name(),
                    exception.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity
                    .status(errorHandler.getStatus())
                    .body(errorHandler);
        }
    }


    @DeleteMapping("/contact/{id}")
    public ResponseEntity removeContact(@PathVariable Long id, HttpServletRequest request) {
        URI uri = URI.create(request.getRequestURI());
        try {
            contactService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            ErrorHandler errorHandler = new ErrorHandler(
                    new Date(),
                    HttpStatus.FORBIDDEN.value(),
                    HttpStatus.FORBIDDEN.name(),
                    exception.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity
                    .status(errorHandler.getStatus())
                    .body(errorHandler);
        }
    }

    @GetMapping("/contacts/{name}")
    public ResponseEntity getContactByName(@PathVariable String name) {
        List<Contact> contacts = contactService.fetchAllByName(name);
        return ResponseEntity.ok().body(contacts);
    }

}
