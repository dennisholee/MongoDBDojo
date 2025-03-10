package io.forest.mongodb.mongock.adapter.mongodb.migration;

import io.forest.mongodb.mongock.adapter.mongodb.document.Books;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.stream.Stream;

@ChangeUnit(id = "config-initializer", order = "000")
@RequiredArgsConstructor
public class MongoDBInitializer {

    @NonNull
    MongoTemplate mongoTemplate;

    @Execution
    public void changeSet() {

        Stream.of(
            Books.of("The Great Gatsby"),
            Books.of("Moby-Dock")
        ).forEach(this.mongoTemplate::save);
    }

    @RollbackExecution
    public void rollback() {
     //   mongoTemplate.deleteMany(new Document());
    }
}
