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
    fun routerFunction(handler: ReactiveHandler):
            RouterFunction<ServerResponse> =
            router {
                ("/reactive").nest {
                    val searchPathName = "search"
                    val savePathName = "save"
                    GET("/{$searchPathName}") { req ->
                        val pathVar = req.pathVariable(searchPathName)
                        ServerResponse.ok().body(
                                handler.getText(pathVar)
                        )
                    }
                    GET("/") {
                        ServerResponse.ok().body(handler.getAllTexts())
                    }
                    PUT("/{$savePathName}") { req ->
                        val pathVar = req.pathVariable(savePathName)
                        ServerResponse.ok().body(
                                handler.addText(pathVar)
                        )
                    }
                }
            }
}

