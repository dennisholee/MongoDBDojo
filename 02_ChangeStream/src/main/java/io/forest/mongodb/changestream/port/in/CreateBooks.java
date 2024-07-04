package io.forest.mongodb.changestream.port.in;

import io.forest.mongodb.changestream.port.dto.BookDto;
import reactor.core.publisher.Mono;

public interface CreateBooks {

	Mono<BookDto> handleRequest(BookDto bookDto);
	
	void handleEvent(BookDto bookDto);
}
