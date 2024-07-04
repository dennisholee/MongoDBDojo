package io.forest.mongodb.changestream.adapter.repository;

import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import io.forest.mongodb.changestream.adapter.repository.model.Book;
import io.forest.mongodb.changestream.adapter.repository.mongodb.BookMongoDBRepository;
import io.forest.mongodb.changestream.port.dto.BookDto;
import io.forest.mongodb.changestream.port.out.BookRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BookReactiveRepository implements BookRepository {

	@NonNull
	BookMongoDBRepository repository;

	@Override
	public Mono<BookDto> save(BookDto dto) {
		Mono.just(UUID.randomUUID()
				.toString());

		Book book = createEntity.apply(generateId, dto);

		return repository.save(book)
				.map(entity2Dto);

	}

	Supplier<String> generateId = () -> UUID.randomUUID()
			.toString();

	BiFunction<Supplier<String>, BookDto, Book> createEntity = (id,
																dto) -> {
		Book book = new Book();
		book.setId(id.get());
		book.setAuthor(dto.getAuthor());
		book.setTitle(dto.getTitle());
		return book;
	};

	Function<Book, BookDto> entity2Dto = entity -> {
		BookDto dto = new BookDto();
		dto.setId(entity.getId());
		dto.setAuthor(entity.getAuthor());
		dto.setTitle(entity.getTitle());
		return dto;
	};
}
