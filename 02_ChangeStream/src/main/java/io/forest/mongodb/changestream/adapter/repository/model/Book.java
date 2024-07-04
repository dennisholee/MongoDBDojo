package io.forest.mongodb.changestream.adapter.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@NoArgsConstructor
@Data
@ToString
public class Book {

	@Id
	String id;

	String title;

	String author;
}
