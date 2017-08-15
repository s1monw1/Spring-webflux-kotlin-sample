package de.swirtz.springwebflux

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Configuration
class RoutingConfiguration {

    @Bean
    fun routerFunction(handler: ReactiveHandler): RouterFunction<ServerResponse> {
        return router {
            ("/reactive").nest {
                GET("/single") {
                    ServerResponse.ok().body(handler.getText())
                }
                GET("/many") {
                    ServerResponse.ok().body(handler.getManyTexts())
                }
            }
        }
    }
}

@Component
class ReactiveHandler {

    fun getText(): Mono<String> =
            Mono.just<Any>("xWorld").map { "Hello $it!" }

    fun getManyTexts(): Flux<String> {
        return Flux.fromArray(arrayOf("first", "second", "third")).map { s -> s.toUpperCase() }
    }
}