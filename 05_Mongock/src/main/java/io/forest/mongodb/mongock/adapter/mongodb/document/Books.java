package io.forest.mongodb.mongock.adapter.mongodb.document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Books")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
public class Books {

    String title;

    public static Books of(String title) {
        return new Books().setTitle(title);
    }
}
