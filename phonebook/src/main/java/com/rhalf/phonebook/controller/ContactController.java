package com.rhalf.phonebook.controller;

import com.rhalf.phonebook.model.Contact;
import com.rhalf.phonebook.model.ErrorHandler;
import com.rhalf.phonebook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public ResponseEntity fetchAll() {
        List<Contact> contacts = contactService.fetchAll();
        return ResponseEntity.ok().body(contacts);
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity find(@PathVariable Long id, HttpServletRequest request) {
        URI uri = URI.create(request.getRequestURI());
        try {
            return ResponseEntity.ok().body(contactService.find(id));
        } catch (Exception e) {
            ErrorHandler error = new ErrorHandler(
                new Date(),
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(),
                    e.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/contact")
    public ResponseEntity save(@RequestBody Contact contact, HttpServletRequest request) {
        URI uri = URI.create(request.getRequestURI());
        try {
            contact.setId(null);
            return ResponseEntity.created(uri).body(contactService.save(contact));
        } catch (Exception e) {
            ErrorHandler error = new ErrorHandler(
                    new Date(),
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(),
                    e.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/contact/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Contact contact, HttpServletRequest request){
        URI uri = URI.create(request.getRequestURI());
        try {
            contact.setId(id);
            return ResponseEntity.ok().body(contactService.update(contact));
        } catch (Exception e) {
            ErrorHandler error = new ErrorHandler(
                    new Date(),
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(),
                    e.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity delete(@PathVariable Long id, HttpServletRequest request){
        URI uri = URI.create(request.getRequestURI());
        try {
            contactService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            ErrorHandler error = new ErrorHandler(
                    new Date(),
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(),
                    e.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity.badRequest().body(error);
        }
    }

}
