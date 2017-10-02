package de.swirtz.springwebflux

import org.junit.*
import org.junit.runner.*
import org.springframework.boot.test.context.*
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.*
import org.springframework.http.*
import org.springframework.test.context.junit4.*
import org.springframework.web.reactive.function.client.*
import reactor.core.publisher.*
import reactor.test.*
import java.time.*
import java.time.temporal.*

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class SpringwebfluxKotlinDemoApplicationTests {

    @LocalServerPort
    var port: Int? = null

    lateinit private var client: WebClient

    @Before
    fun setup() {
        client = WebClient.create("http://localhost:$port")
    }

    @Test
    fun simpleCreation() {
        val words = arrayListOf(
                "the",
                "quick",
                "brown",
                "fox",
                "jumped",
                "over",
                "the",
                "lazy",
                "dog"
        )
        val helloPauseWorld = Mono.just("Hello")
                .concatWith(Mono.just("world")
                        .delaySubscription(Duration.of(500, ChronoUnit.MILLIS)))

        helloPauseWorld.toStream().forEach {
            println(it)
        }
    }

    @Test
    fun getOnReactiveShouldReturnOk() {
        client
                .get().uri("/reactive")
                .exchange().test()
                .expectNextMatches { it.statusCode() == HttpStatus.OK }
    }

    @Test
    fun retrievedFluxShouldStartWithResult() {
        client
                .get().uri("/reactive")
                .retrieve()
                .bodyToFlux<String>()
                .test()
                .expectNextMatches { it.startsWith("Result:") }
                .verifyComplete()
    }

    @Test
    fun getOnReactiveSearchShouldReturnOk() {
        client
                .get().uri("/reactive/search")
                .exchange().test()
                .expectNextMatches { it.statusCode() == HttpStatus.OK }
    }

    @Test
    fun getOnReactiveSearchShouldStartWithResult() {
        client
                .get().uri("/reactive/search")
                .retrieve()
                .bodyToMono<String>()
                .test()
                .expectNextMatches { it.startsWith("Result:") }
                .verifyComplete()
    }

    @Test
    fun putOnReactiveSearchShouldReturnTrue() {
        client
                .put().uri("/reactive/search?save=test")
                .retrieve()
                .bodyToMono<String>()
                .test()
                .expectNextMatches { it == "Result: true!" }
                .verifyComplete()
    }

}
