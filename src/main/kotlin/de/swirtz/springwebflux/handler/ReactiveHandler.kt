package de.swirtz.springwebflux.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono

@Component
class ReactiveHandler {
    @Autowired
    lateinit var repo: NamesRepo

    fun getName(search: String): Mono<String> = repo.get(search).toMono().map { "Result: $it!" }
    fun addName(text: String): Mono<String> = repo.add(text).toMono().map { "Result: $it!" }
    fun getAllNames(): Flux<String> = repo.getAll().toFlux().map { "Result: $it" }
}

@Repository
class NamesRepo {
    private val entities = mutableListOf<String>()
    fun add(name: String) = entities.add(name)
    fun get(name: String) = entities.find { it == name } ?: "not found!"
    fun getAll() = listOf(entities)
}
