package io.forest.mongodb.changestream.adapter.repository.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import io.forest.mongodb.changestream.adapter.repository.model.Book;

public interface BookMongoDBRepository extends ReactiveMongoRepository<Book, String> {

}
