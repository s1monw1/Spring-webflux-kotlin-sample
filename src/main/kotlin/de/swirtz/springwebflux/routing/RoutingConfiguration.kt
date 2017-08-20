package de.swirtz.springwebflux.routing

import de.swirtz.springwebflux.handler.ReactiveHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router

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

