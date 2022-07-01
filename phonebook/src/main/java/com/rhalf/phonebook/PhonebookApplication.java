package com.rhalf.phonebook;

import com.rhalf.phonebook.model.Contact;
import com.rhalf.phonebook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhonebookApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PhonebookApplication.class, args);
	}

	@Autowired
	private ContactService contactService;

	@Override
	public void run(String... args) throws Exception {
		contactService.save(new Contact(
				null,
				"rhalf",
				"09176088771",
				"rhalfcaacbay@gmail.com",
				true
		));
		contactService.save(new Contact(
				null,
				"mark",
				"091760123123",
				"mark@lapu.com",
				true
		));
	}
}
