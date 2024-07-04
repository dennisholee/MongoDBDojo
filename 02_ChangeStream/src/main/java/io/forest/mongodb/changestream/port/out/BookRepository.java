package io.forest.mongodb.changestream.port.out;

import io.forest.mongodb.changestream.port.dto.BookDto;
import reactor.core.publisher.Mono;

public interface BookRepository {

	Mono<BookDto> save(BookDto dto);
}
