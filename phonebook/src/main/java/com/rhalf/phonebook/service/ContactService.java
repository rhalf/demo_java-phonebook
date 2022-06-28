package com.rhalf.phonebook.service;

import com.rhalf.phonebook.model.Contact;
import com.rhalf.phonebook.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@Slf4j
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> fetchAll() {
        return contactRepository.findAll();
    }

    public List<Contact> fetchAllByName(String name) {
        return contactRepository.findByNameContaining(name);
    }

    public Contact save(Contact contact) throws Exception {
        try {
            return contactRepository.saveAndFlush(contact);
        } catch (Exception exception) {
            Throwable t = exception.getCause();
            while (t.getCause() != null) t = t.getCause();
            throw new Exception(t.getLocalizedMessage());
        }
    }

    public Contact findById(Long id) throws Exception {
        Contact contact = contactRepository.findById(id).orElse(null);
        String message = MessageFormat.format("Contact id={0} doesn't exist!", id);
        if (contact == null) throw new Exception(message);
        else return contact;
    }

    public void delete(Long id) throws Exception {
        Contact contact = this.findById(id);
        contactRepository.delete(contact);
    }

    public Contact update(Long id, Contact contact) throws Exception {
        contact.setId(id);
        this.findById(id);
        return contactRepository.save(contact);
    }
}
