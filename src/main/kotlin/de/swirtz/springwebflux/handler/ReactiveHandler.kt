package de.swirtz.springwebflux.handler

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono

@Component
class ReactiveHandler(val repo: StringRepo) {
    fun getText(search: String): Mono<String> = repo.get(search).toMono().map { "Result: $it!" }
    fun addText(text: String): Mono<String> = repo.add(text).toMono().map { "Result: $it!" }
    fun getAllTexts(): Flux<String> = repo.getAll().toFlux().map { "Result: $it" }
}

@Component
class StringRepo {
    private val entities = mutableListOf<String>()
    fun add(s: String) = entities.add(s)
    fun get(s: String) = entities.find { it == s } ?: "not found!"
    fun getAll() = listOf(entities)
}