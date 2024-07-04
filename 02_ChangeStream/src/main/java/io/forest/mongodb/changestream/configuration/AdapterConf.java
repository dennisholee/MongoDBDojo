package io.forest.mongodb.changestream.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import io.forest.mongodb.changestream.adapter.BookMongoDBChangeStream;
import io.forest.mongodb.changestream.adapter.repository.BookReactiveRepository;
import io.forest.mongodb.changestream.adapter.repository.mongodb.BookMongoDBRepository;
import io.forest.mongodb.changestream.port.in.CreateBooks;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "io.forest.mongodb.changestream.adapter.repository.mongodb")
public class AdapterConf extends AbstractReactiveMongoConfiguration {

	@Value("${spring.data.mongodb.database}")
	String databaseName;

	@Value("${spring.data.mongodb.uri}")
	String databaseUri;

	@Override
	public MongoClient reactiveMongoClient() {
		ServerApi serverApi = ServerApi.builder()
				.version(ServerApiVersion.V1)
				.build();
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
				.applyConnectionString(new ConnectionString(databaseUri))
				.serverApi(serverApi)
				.build();

		return MongoClients.create(mongoClientSettings);
	}

	@Override
	protected String getDatabaseName() {
		return databaseName;
	}

	@Bean
	public ReactiveMongoTemplate reactiveMongoTemplate(@Autowired MongoClient mongoClient) {
		return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
	}

	@Bean
	BookMongoDBChangeStream userMongoDBChangeStream(@Autowired ReactiveMongoTemplate reactiveMongoTemplate,
													@Autowired CreateBooks createBooks) {
		return new BookMongoDBChangeStream(reactiveMongoTemplate, createBooks);
	}

	@Bean
	BookReactiveRepository bookReactiveRepository(@Autowired BookMongoDBRepository bookMongoDBRepository) {
		return new BookReactiveRepository(bookMongoDBRepository);
	}
}
