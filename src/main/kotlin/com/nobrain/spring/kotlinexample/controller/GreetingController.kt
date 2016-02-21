package com.nobrain.spring.kotlinexample.controller

import com.nobrain.spring.kotlinexample.domain.Greeting
import com.nobrain.spring.kotlinexample.domain.User
import com.nobrain.spring.kotlinexample.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by jsuch2362 on 2016. 2. 21..
 */
@RestController
class GreetingController {

    @Autowired
    lateinit var userRepository:UserRepository

    @RequestMapping("/")
    fun greeting(@RequestParam(value = "name", defaultValue = "world") name : String): Greeting {
        return Greeting("Hello $name")
    }

    @RequestMapping("/users")
    fun getUsers(@RequestParam(value = "name", defaultValue = "world") name : String) = userRepository.findAll()

    @RequestMapping(value = "/user", method = arrayOf(RequestMethod.POST))
    fun addUser(@RequestParam(value = "name", defaultValue = "world") name : String) :User {
        return userRepository.save(User(name = name))
    }

}