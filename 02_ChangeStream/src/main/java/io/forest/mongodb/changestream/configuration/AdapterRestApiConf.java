package io.forest.mongodb.changestream.configuration;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.forest.mongodb.changestream.port.dto.BookDto;
import io.forest.mongodb.changestream.port.in.CreateBooks;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
public class AdapterRestApiConf {

	@Autowired(required = true)
	CreateBooks createBooks;

	@Bean
	RouterFunction<ServerResponse> composedNotifyRoutes() {
		return route().POST("/book", accept(MediaType.APPLICATION_JSON), this::createClaim)
				.build();
	}

	Mono<ServerResponse> createClaim(ServerRequest request) {
		log.info("Request received");

		return request.bodyToMono(BookDto.class)
				.doOnNext(n -> log.info("Received request payload [bookDto={}]", n))
				.flatMap(createBooks::handleRequest)
				.doOnNext(n -> log.info("Book created [bookDto={}]", n))
				.flatMap(c -> ServerResponse.ok()
						.bodyValue(c));
	}

}
