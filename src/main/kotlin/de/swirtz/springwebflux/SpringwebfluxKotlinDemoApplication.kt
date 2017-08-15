package de.swirtz.springwebflux

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Spring WebFlux is the new reactive web framework introduced in Spring Framework 5.0.
 * Unlike Spring MVC, it does not require the Servlet API, is fully asynchronous and non-blocking,
 * and implements the Reactive Streams specification through the Reactor project.
 */
@SpringBootApplication
class SpringwebfluxKotlinDemoApplication {

    companion object {
        val LOG = LoggerFactory.getLogger(SpringwebfluxKotlinDemoApplication::class.java)
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(SpringwebfluxKotlinDemoApplication::class.java, *args)
}

