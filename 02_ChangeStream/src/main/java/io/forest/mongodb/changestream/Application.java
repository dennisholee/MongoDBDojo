package io.forest.mongodb.changestream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Import;

import io.forest.mongodb.changestream.configuration.AdapterConf;
import io.forest.mongodb.changestream.configuration.AdapterRestApiConf;
import io.forest.mongodb.changestream.configuration.ApplicationConf;

@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@Import({ AdapterConf.class, AdapterRestApiConf.class, ApplicationConf.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
