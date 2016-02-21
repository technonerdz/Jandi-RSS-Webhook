package com.nobrain.spring.kotlinexample.controller

import com.nobrain.spring.kotlinexample.domain.Greeting
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by jsuch2362 on 2016. 2. 21..
 */
@RestController
class GreetingController {

    @RequestMapping("/")
    fun greeting(@RequestParam(value = "name", defaultValue = "world") name : String): Greeting {
        return Greeting("Hello $name")
    }
}