package io.forest.mongodb.changestream.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import io.forest.mongodb.changestream.application.CreateBooksApplication;
import io.forest.mongodb.changestream.port.in.CreateBooks;
import io.forest.mongodb.changestream.port.out.BookRepository;

public class ApplicationConf {

	@Bean
	CreateBooks createBookApplication(@Autowired BookRepository bookRepository) {
		return new CreateBooksApplication(bookRepository);
	}
}
