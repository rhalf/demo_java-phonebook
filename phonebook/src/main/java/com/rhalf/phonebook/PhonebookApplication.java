package com.rhalf.phonebook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@Slf4j
public class PhonebookApplication implements  CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(PhonebookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("CommandLinner: starts =================================");


		log.info("CommandLinner: ends =================================");
	}
}
