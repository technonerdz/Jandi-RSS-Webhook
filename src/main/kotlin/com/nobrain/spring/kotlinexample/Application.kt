package com.nobrain.spring.kotlinexample

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@EnableAutoConfiguration
open class Application {

    companion object {
        @JvmStatic public fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)

        }
    }
}