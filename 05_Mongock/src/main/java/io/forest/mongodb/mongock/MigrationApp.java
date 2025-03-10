package io.forest.mongodb.mongock;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMongock
@SpringBootApplication
public class MigrationApp {
    public static void main(String[] args) {
        SpringApplication.run(MigrationApp.class, args);
    }
}
