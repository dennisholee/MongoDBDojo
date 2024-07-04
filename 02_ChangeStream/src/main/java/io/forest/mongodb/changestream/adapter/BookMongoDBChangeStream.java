package io.forest.mongodb.changestream.adapter;

import java.util.function.Function;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import io.forest.mongodb.changestream.adapter.repository.model.Book;
import io.forest.mongodb.changestream.port.dto.BookDto;
import io.forest.mongodb.changestream.port.in.CreateBooks;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Log4j2
@RequiredArgsConstructor
public class BookMongoDBChangeStream {

	@NonNull
	ReactiveMongoTemplate reactiveMongoTemplate;

	@NonNull
	CreateBooks createBooks;

	@EventListener(ContextRefreshedEvent.class)
	public void init() {
		this.subscribe()
				.map(entity2Dto)
				.doOnNext(createBooks::handleEvent)
				.subscribe();
	}

	Flux<Book> subscribe() {
		log.info("Loading change stream.");

		return reactiveMongoTemplate.changeStream(Book.class)
				.watchCollection("book")
				.listen()
				.map(ChangeStreamEvent::getBody);
	}

	Function<Book, BookDto> entity2Dto = entity -> {
		BookDto dto = new BookDto();
		dto.setId(entity.getId());
		dto.setAuthor(entity.getAuthor());
		dto.setTitle(entity.getTitle());
		return dto;
	};
}
