package io.forest.mongodb.changestream.application;

import io.forest.mongodb.changestream.port.dto.BookDto;
import io.forest.mongodb.changestream.port.in.CreateBooks;
import io.forest.mongodb.changestream.port.out.BookRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Log4j2
public class CreateBooksApplication implements CreateBooks {

	@NonNull
	BookRepository bookRepository;

	public Mono<BookDto> handleRequest(BookDto bookDto) {
		return this.bookRepository.save(bookDto);
	}

	@Override
	public void handleEvent(BookDto bookDto) {
		log.info("Processing event [bookDto={}]", bookDto);
	}
	
	

}
