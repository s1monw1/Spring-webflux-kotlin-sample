package de.swirtz.springwebflux.handler

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono

@Component
class ReactiveHandler {

    fun getText(): Mono<String> = ("xWorld").toMono().map { "Hello $it!" }

    fun getManyTexts(): Flux<String> {
        return arrayOf("first", "second", "third").toFlux().map { s -> s.toUpperCase() }
    }
}