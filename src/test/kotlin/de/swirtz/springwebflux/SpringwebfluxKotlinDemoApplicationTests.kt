package de.swirtz.springwebflux

import org.junit.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.concurrent.CountDownLatch


//@RunWith(SpringRunner::class)
//@SpringBootTest
class SpringwebfluxKotlinDemoApplicationTests {

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

}
