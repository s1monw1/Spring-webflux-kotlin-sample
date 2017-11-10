package de.swirtz.springwebflux

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.test.test

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class WebfluxKotlinDemoApplicationTests {

    @LocalServerPort
    var port: Int? = null

    lateinit private var client: WebClient

    @Before
    fun setup() {
        client = WebClient.create("http://localhost:$port")
    }


    @Test
    fun getOnReactiveShouldReturnOk() {
        client.get().uri("/reactive")
                .exchange().test()
                .expectNextMatches { it.statusCode() == HttpStatus.OK }
    }

    @Test
    fun retrievedFluxShouldStartWithResult() {
        client.get().uri("/reactive")
                .retrieve()
                .bodyToFlux<String>()
                .test()
                .expectNextMatches { it.startsWith("Result:") }
                .verifyComplete()
    }

    @Test
    fun getOnReactiveSearchShouldReturnOk() {
        client.get().uri("/reactive/search")
                .exchange().test()
                .expectNextMatches { it.statusCode() == HttpStatus.OK }
    }

    @Test
    fun getOnReactiveSearchShouldStartWithResult() {
        client.get().uri("/reactive/search")
                .retrieve()
                .bodyToMono<String>()
                .test()
                .expectNextMatches { it.startsWith("Result:") }
                .verifyComplete()
    }

    @Test
    fun putOnReactiveSearchShouldReturnTrue() {
        client.put().uri("/reactive/search?save=test")
                .retrieve()
                .bodyToMono<String>()
                .test()
                .expectNextMatches { it == "Result: true!" }
                .verifyComplete()
    }

}
