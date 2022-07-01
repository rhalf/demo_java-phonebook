package com.rhalf.phonebook.repository;

import com.rhalf.phonebook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
